package top.zhx47.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.zhx47.api.admin.datasource.dto.PageDTO;
import top.zhx47.api.admin.datasource.dto.ReceiveInfoDTO;
import top.zhx47.common.redis.service.RedisService;
import top.zhx47.common.security.datasource.dto.UserDetailsDTO;
import top.zhx47.common.security.service.TokenService;
import top.zhx47.common.security.utils.SecurityUtils;
import top.zhx47.admin.datasource.entity.ActivationCode;
import top.zhx47.admin.datasource.entity.SysSite;
import top.zhx47.admin.datasource.entity.User;
import top.zhx47.admin.datasource.entity.UserSiteCollect;
import top.zhx47.admin.mapper.UserMapper;
import top.zhx47.admin.service.ActivationCodeService;
import top.zhx47.admin.service.SysSiteService;
import top.zhx47.admin.service.UserService;
import top.zhx47.admin.service.UserSiteCollectService;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/10 22:36
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserSiteCollectService userSiteCollectService;
    private final ActivationCodeService activationCodeService;
    private final SysSiteService sysSiteService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    @Override
    public User getUser() {
        return this.getById(SecurityUtils.getUsername());
    }

    @Override
    @Transactional
    public UserSiteCollect addCollect(String siteId, String type) {
        UserSiteCollect userSiteCollect = null;
        // 1. 获取该平台的详细信息，验证siteId是否有效
        SysSite sysSite = sysSiteService.getById(siteId);
        Assert.notNull(sysSite, "添加平台参数错误！！！");
        // 2. 获取当前用户，当前平台的平台列表，用于控制是否创建分身
        List<UserSiteCollect> userSiteCollectList = userSiteCollectService.queryByGroupAndUserId(siteId, SecurityUtils.getUsername());
        // 3. 添加平台到列表中
        if (userSiteCollectList.isEmpty()) {
            // 3.1 用户平台列表当前无该平台，无需已分身形式创建
            userSiteCollect = new UserSiteCollect(SecurityUtils.getUsername(), siteId, siteId, "", null, null);
            userSiteCollectService.save(userSiteCollect);
        } else if ("copy".equals(type)) {
            // 3.2 以分身的形式创建平台
            String collectId = getCollectId(userSiteCollectList);
            userSiteCollect = new UserSiteCollect(SecurityUtils.getUsername(), siteId + collectId, siteId, collectId, null, null);
            userSiteCollectService.save(userSiteCollect);
        }
        return userSiteCollect;
    }

    @Override
    public User getUserByPhone(String phone) {
        return baseMapper.loadUserByPhone(phone);
    }

    @Override
    public String login(String phone, String password) {
        // 用户验证
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(phone, password));
        } catch (Exception e) {
            return "";
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

    @Override
    public List<ReceiveInfoDTO> getAddress() {
        Integer id = SecurityUtils.getId();
        ReceiveInfoDTO receiveInfoDTO = this.baseMapper.getAddress(id);
        LOGGER.info("获取用户：{} 收获信息：{}", id, receiveInfoDTO);
        List<ReceiveInfoDTO> list = new ArrayList<>();
        if (receiveInfoDTO != null) {
            list.add(receiveInfoDTO);
        }
        return list;
    }

    @Override
    public void addAddress(ReceiveInfoDTO receiveInfoDTO) {
        this.baseMapper.addAddress(receiveInfoDTO, SecurityUtils.getId());
    }

    /**
     * 获取分身的index序号
     */
    private String getCollectId(List<UserSiteCollect> userSiteCollectList) {
        if (!"".equals(userSiteCollectList.get(0).getIndex())) {
            return "";
        }
        if (userSiteCollectList.size() == 1) {
            return "1";
        }
        List<Integer> collectIndexs = userSiteCollectList.stream().map(o -> {
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
