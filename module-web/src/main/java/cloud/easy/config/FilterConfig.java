package cloud.easy.config;

import cloud.easy.filter.ApiHeaderFilter;
import cloud.easy.filter.CorsFilter;
import cloud.easy.filter.LogParamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Title: FilterConfig
 * Description:
 *
 * @author Xu Honglin
 * @version 1.0
 */
@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<Filter> corsFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(new CorsFilter());
        filterReg.setOrder(-1);
        filterReg.setName("CorsFilter");
        filterReg.addUrlPatterns("/*");
        return filterReg;
    }

    @Bean
    FilterRegistrationBean<Filter> paramFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(new ApiHeaderFilter());
        filterReg.setOrder(0);
        filterReg.setName("ApiHeaderFilter");
        filterReg.addUrlPatterns("/api/*");
        return filterReg;
    }

    @Bean
    FilterRegistrationBean<Filter> logFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(new LogParamFilter());
        filterReg.setOrder(1);
        filterReg.setName("LogParamFilter");
        filterReg.addUrlPatterns("/api/*");
        return filterReg;
    }

}
