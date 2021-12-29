package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.zhx47.common.core.exception.user.UserPasswordNotMatchException;
import top.zhx47.common.core.utils.ShareCodeUtils;
import top.zhx47.common.redis.service.RedisService;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.service.TokenService;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.qxx.api.datasource.dto.PageDTO;
import top.zhx47.qxx.api.datasource.dto.UserDTO;
import top.zhx47.qxx.datasource.entity.ActivationCode;
import top.zhx47.qxx.datasource.entity.Collect;
import top.zhx47.qxx.datasource.entity.Site;
import top.zhx47.qxx.datasource.entity.User;
import top.zhx47.qxx.mapper.UserMapper;
import top.zhx47.qxx.service.ActivationCodeService;
import top.zhx47.qxx.service.CollectService;
import top.zhx47.qxx.service.SiteService;
import top.zhx47.qxx.service.UserService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ActivationCodeService activationCodeService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisService redisService;

    @Override
    public Boolean registerUser(UserDTO registerOrResetDTO) throws Exception {
        // 获取邀请人，邀请人为空视为无效
        Long parentId = ShareCodeUtils.codeToId(registerOrResetDTO.getRecomender());
        if (this.getById(parentId) == null) {
            return false;
        }
        synchronized (UserServiceImpl.class) {
            if (redisService.hasKey(registerOrResetDTO.getPhone())) {
                return false;
            }
            redisService.expire(registerOrResetDTO.getPhone(), 60);
            User user = this.baseMapper.loadUserByPhone(registerOrResetDTO.getPhone());
            if (user != null) {
                return false;
            }
        }
        User user = new User();
        user.setPhone(registerOrResetDTO.getPhone());
        user.setPassword(passwordEncoder.encode(registerOrResetDTO.getPassword()));
        user.setParendId(parentId.toString());
        this.save(user);
        String code = ShareCodeUtils.idToCode(Long.valueOf(user.getId()));
        user.setRecordId(code);
        updateById(user);
        return true;
    }

    @Override
    public User getUser() {
        return this.getById(SecurityUtils.getUsername());
    }

    @Override
    @Transactional
    public Collect addCollect(String siteId, String type) {
        Collect collect = null;
        // 1. 获取该平台的详细信息，验证siteId是否有效
        Site site = siteService.getById(siteId);
        Assert.notNull(site, "添加平台参数错误！！！");
        // 2. 获取当前用户，当前平台的平台列表，用于控制是否创建分身
        List<Collect> collectList = collectService.queryByGroupAndUserId(siteId, SecurityUtils.getUsername());
        // 3. 添加平台到列表中
        if (collectList.isEmpty()) {
            // 3.1 用户平台列表当前无该平台，无需已分身形式创建
            collect = new Collect(SecurityUtils.getUsername(), siteId, siteId, "", null, null);
            collectService.save(collect);
        } else if ("copy".equals(type)) {
            // 3.2 以分身的形式创建平台
            String collectId = getCollectId(collectList);
            collect = new Collect(SecurityUtils.getUsername(), siteId + collectId, siteId, collectId, null, null);
            collectService.save(collect);
        }
        return collect;
    }

    @Override
    public User getUserByPhone(String phone) {
        return baseMapper.loadUserByPhone(phone);
    }

    @Override
    public String login(String phone, String password) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(phone, password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserPasswordNotMatchException();
        }
        UserDetailsDTO loginUser = (UserDetailsDTO) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    @Override
    public Long countFriend() {
        return baseMapper.countFriend(SecurityUtils.getId());
    }

    @Override
    public List<User> getFriendListByPage(PageDTO pageDTO) {
        return baseMapper.getFriendListByPage(SecurityUtils.getId(), (pageDTO.getPageIndex() - 1) * pageDTO.getPageNum(), pageDTO.getPageNum());
    }

    @Override
    @Transactional
    public boolean payCode(String code) {
        synchronized (UserServiceImpl.class) {
            if (redisService.hasKey(code)) {
                return false;
            }
            redisService.expire(code, 60);
        }
        ActivationCode activationCode = activationCodeService.queryByCode(code);
        if (activationCode == null || activationCode.getEnable().equals(1)) {
            return false;
        }
        User user = this.getById(SecurityUtils.getId());
        LocalDate expireTime = user.getExpireTime();
        if (expireTime.compareTo(LocalDate.now()) > 0) {
            // 当前为会员
            user.setExpireTime(expireTime.plusDays(Long.valueOf(activationCode.getDay())));
        } else {
            // 非会员
            user.setExpireTime(LocalDate.now().plusDays(Long.valueOf(activationCode.getDay())));
        }
        activationCode.setEnable(1);
        this.updateById(user);
        this.activationCodeService.updateById(activationCode);
        return true;
    }

    @Override
    public User loadUserByPhone(@NotNull String phone) {
        return this.baseMapper.loadUserByPhone(phone);
    }

    /**
     * 获取分身的index序号
     *
     * @param collectList
     */
    private String getCollectId(List<Collect> collectList) {
        if (!"".equals(collectList.get(0).getIndex())) {
            return "";
        }
        if (collectList.size() == 1) {
            return "1";
        }
        List<Integer> collectIndexs = collectList.stream().map(o -> {
            if ("".equals(o.getIndex())) {
                return 0;
            } else {
                return new Integer(o.getIndex());
            }
        }).collect(Collectors.toList());
        for (int i = 1; i < collectIndexs.size(); i++) {
            if (collectIndexs.get(i).equals(collectIndexs.get(i - 1) + 1)) {
                continue;
            }
            return String.valueOf(i);
        }
        return String.valueOf(collectIndexs.size());
    }
}
