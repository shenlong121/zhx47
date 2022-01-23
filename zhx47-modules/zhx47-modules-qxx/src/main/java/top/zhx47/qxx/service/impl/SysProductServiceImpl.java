package top.zhx47.qxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.zhx47.qxx.datasource.entity.SysProduct;
import top.zhx47.qxx.mapper.SysProductMapper;
import top.zhx47.qxx.service.SysProductService;

@Service
public class SysProductServiceImpl extends ServiceImpl<SysProductMapper, SysProduct> implements SysProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysProductServiceImpl.class);
}
