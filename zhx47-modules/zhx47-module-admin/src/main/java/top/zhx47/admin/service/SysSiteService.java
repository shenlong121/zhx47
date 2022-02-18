package top.zhx47.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhx47.admin.datasource.entity.SysSite;

import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 0:10
 */
public interface SysSiteService extends IService<SysSite> {
    List<SysSite> getSysSiteList();
}
