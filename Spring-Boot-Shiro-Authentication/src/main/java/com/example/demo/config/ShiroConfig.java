package com.example.demo.config;

import com.example.demo.shiro.ShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/1/18 20:57
 * @Version: 1.0
 **/

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login"); //登录的url
        shiroFilterFactoryBean.setSuccessUrl("/index"); //登录成功跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403"); //未授权跳转的url
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 定义filterChain 配置静态资源不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        // druid数据源监控页面不拦截
        filterChainDefinitionMap.put("druid/**", "anon");
        // 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        // 其他url都需要登录验证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    private SimpleCookie rememberMeCookie(){
        // 设置cookie的名称，这里对应login页面中的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间
        cookie.setMaxAge(120);
        return cookie;
    }

    private CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public SecurityManager securityManager(){
        // 配置SecurityManager，并注入shiroRealm
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(shiroRealm());
        defaultSecurityManager.setRememberMeManager(rememberMeManager());
        defaultSecurityManager.setCacheManager(ehCacheManager());
        return defaultSecurityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    private RedisManager redisManager(){
        return new RedisManager();
    }

    private RedisCacheManager redisCacheManager(){
        RedisCacheManager cache = new RedisCacheManager();
        cache.setRedisManager(redisManager());
        return cache;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:config/shiro-ehcache.xml");
        return manager;

    }
}
