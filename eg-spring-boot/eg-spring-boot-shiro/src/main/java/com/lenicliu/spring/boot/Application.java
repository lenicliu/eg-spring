package com.lenicliu.spring.boot;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "characterEncodingFilter")
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addInitParameter("encoding", "UTF-8");
		bean.addInitParameter("forceEncoding", "true");
		bean.setFilter(new CharacterEncodingFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setSuccessUrl("/index");
		shiroFilter.setUnauthorizedUrl("/forbidden");
		Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();

		filterChainDefinitionMapping.put("/", "anon");
		filterChainDefinitionMapping.put("/home", "authc,roles[guest]");
		filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
		shiroFilter.setSecurityManager(securityManager());

		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("anon", new AnonymousFilter());
		filters.put("authc", new FormAuthenticationFilter());
		filters.put("logout", new LogoutFilter());
		filters.put("roles", new RolesAuthorizationFilter());
		filters.put("user", new UserFilter());
		shiroFilter.setFilters(filters);
		return shiroFilter;
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm());
		return securityManager;
	}

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	public PropertiesRealm realm() {
		PropertiesRealm propertiesRealm = new PropertiesRealm();
		propertiesRealm.init();
		return propertiesRealm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}