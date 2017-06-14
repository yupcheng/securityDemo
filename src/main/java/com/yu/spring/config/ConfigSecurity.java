//package com.yu.spring.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 通过注解配置 security
// * Created by Administrator on 2017/6/7.
// */
//@Configuration
//@EnableWebSecurity
//public class ConfigSecurity extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("yiibai").password("123456").roles("USER");
//    }
//
//    //.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/admin/**").access("hasRole('ROLE_USER')")
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
//                .and()
//                .csrf();
//    }
//
//    /**
//     *
//     Spring Security的XML文件相当于以下配置：
//
//
//     <http auto-config="true">
//     <intercept-url pattern="/admin**" access="ROLE_USER" />
//     <form-login
//     login-page="/login"
//     default-target-url="/welcome"
//     authentication-failure-url="/login?error"
//     username-parameter="username"
//     password-parameter="password" />
//     <logout logout-success-url="/login?logout" />
//     <!-- enable csrf protection -->
//     <csrf/>
//     </http>
//
//     <authentication-manager>
//     <authentication-provider>
//     <user-service>
//     <user name="yiibai" password="123456" authorities="ROLE_USER" />
//     </user-service>
//     </authentication-provider>
//     </authentication-manager>
//
//
//     */
//
//}
