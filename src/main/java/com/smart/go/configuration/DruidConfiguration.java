package com.smart.go.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description  Druid配置
 * Author cloudr
 * Date 2020/6/18 14:13
 * Version 1.0
 **/
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 白名单 也可添加黑名单 第一个参数改为“deny”即可(deny优先级高于allow)
//        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 监控页面登陆账号
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        // 监控页面登陆密码
        servletRegistrationBean.addInitParameter("loginPassword", "1111");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加需要忽略的格式
        filterRegistrationBean.addInitParameter("/exclusions", "*.js,*.jpg,*.png,*/css,/druid/*");
        return filterRegistrationBean;
    }
}
