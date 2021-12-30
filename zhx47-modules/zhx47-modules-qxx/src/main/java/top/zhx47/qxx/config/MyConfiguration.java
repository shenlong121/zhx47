package top.zhx47.qxx.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.zhx47.qxx.datasource.po.SystemInfoPO;
import top.zhx47.qxx.service.PlatformInfoService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张许
 * @Description:
 * @Date: 2021/6/12 1:55
 */

@Configuration
public class MyConfiguration implements WebMvcConfigurer {

    private final PlatformInfoService platformInfoService;

    @Autowired
    public MyConfiguration(PlatformInfoService platformInfoService) {
        Assert.notNull(platformInfoService, "platformInfoService must not be null!");
        this.platformInfoService = platformInfoService;
    }

    /**
     * 配置消息转换器
     * new HttpMessageConverters(true, converters);
     * 一定要设为true才能替换否则不会替换
     *
     * @return 返回一个消息转换的bean
     */
    @Bean
    public HttpMessageConverters fastJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        //需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //全局时间配置
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setDateFormat("yyyy-MM-dd");
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //在convert中添加配置信息.
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, fastConverter);
        return new HttpMessageConverters(converters);
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() throws IllegalAccessException {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        // 设置访问源地址
        config.addAllowedOrigin(this.getSystemInfoPO().getQxxFont());
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * 从数据库中获取系统信息
     */
    @Bean
    public SystemInfoPO getSystemInfoPO() throws IllegalAccessException {
        return this.platformInfoService.getSystemInfoPO();
    }
}
