package cn.fanchencloud.airport.config;

import cn.fanchencloud.airport.config.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午5:24
 * Description:
 *
 * @author chen
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public FilterRegistrationBean<UserFilter> userFilter() {
        FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserFilter());
        registration.addUrlPatterns("/*");
        registration.setName("userFilter");
        registration.setOrder(1);
        return registration;
    }
}
