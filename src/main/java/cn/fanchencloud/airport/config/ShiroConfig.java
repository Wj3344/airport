package cn.fanchencloud.airport.config;


import cn.fanchencloud.airport.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-5
 * Time: 下午6:05
 * Description:
 *
 * @author chen
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //使用 LinkedHashMap 保证添加后有序
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //定义filterChain，静态资源不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        //druid 数据源监控界面不拦截
        filterChainDefinitionMap.put("/druid/**", "anon");
        //配置退出过滤器，其中具体的退出代码 Shiro 已经帮我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        //除以上所有 url 都必须通过认证才可以访问，未通过认证自动访问 loginurl
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        //配置 SecurityManager，并注入 shiroRealm，注意不是DefaultSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        //shiro生命周期处理器
        return new LifecycleBeanPostProcessor();
    }

    /************** 开启Shiro 角色权限控制注解 start  ******************/
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /************** 开启Shiro 角色权限控制注解 end  ******************/

    @Bean
    public ShiroRealm shiroRealm() {
        //配置 Realm，需要自己实现
        return new ShiroRealm();
    }

}
