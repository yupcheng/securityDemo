package com.yu.spring.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * 用户访问权限判断
 * Created by Administrator on 2017/6/15.
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            if (authentication == null) {
                throw new AccessDeniedException("当前访问没有权限");
            }
            ConfigAttribute configAttribute = iterator.next();
            String needCode = configAttribute.getAttribute();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //当前用户角色权限与访问资源需要的权限对比
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needCode) ) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("当前访问没有权限");
    }
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
