package top.zhx47.admin.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.zhx47.common.core.utils.AlipayUtils;
import top.zhx47.common.core.utils.ServletUtils;
import top.zhx47.admin.datasource.entity.SysOrder;
import top.zhx47.admin.datasource.entity.User;
import top.zhx47.admin.mapper.SysOrderMapper;
import top.zhx47.admin.service.PlatformInfoService;
import top.zhx47.admin.service.SysOrderService;
import top.zhx47.admin.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysOrderServiceImpl.class);

    private final UserService userService;
    private final PlatformInfoService platformInfoService;

}
