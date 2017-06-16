package com.yu.spring.security;

import com.yu.spring.dao.MenuDao;
import com.yu.spring.dao.PrivilegeDao;
import com.yu.spring.entity.*;
import com.yu.spring.service.MenuService;
import com.yu.spring.service.PrivilegeService;
import com.yu.spring.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * 负责提供身份验证细节验证管理
 * 使用UserDAO对象从数据库中获得的数据来填充此对象。
 * Created by Administrator on 2017/6/7.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    protected final Log logger;

    {
        logger = LogFactory.getLog(getClass());
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private MenuService menuService;

    /**
     * 根据用户名获取到用户的权限并封装成GrantedAuthority集合
     *
     * @param uid
     */
    @Transactional(readOnly=true)
    public Collection<GrantedAuthority> loadUserAuthorities(Integer uid) {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        try {
            List<Privilege> list = this.privilegeService.queryPrivilegeByUid(uid);// 从数据库中获取所有权限
            for (Privilege authority : list) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getMark());
                auths.add(grantedAuthority);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
        }
        try {
            List<Menu> list = this.menuService.queryMenuByUid(uid);// .queryPrivilegeByUid(uid);//
            for (Menu authority : list) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getMaker());
                auths.add(grantedAuthority);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return auths;
    }


    /**
     * 通过用户名加载用户权限
     * @param ssoId
     * @return
     * @throws RuntimeException
     */

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername (String ssoId) throws RuntimeException
    {
        User user = userService.findBySso(ssoId);
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        System.out.println("User : "+user);
        user = userService.findBySso(ssoId);
        if (user == null) {
            throw new RuntimeException("can not find user : " + ssoId);
        }
        auths = this.loadUserAuthorities(user.getId());// 获取该用户所有权限
        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
                user.getState().equals("Active"), true, true, true, auths);
    }

    /**
     * 设置用户角色数据
     * 角色列表
     * @param user
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        /**
         * 循环遍历设置角色列表信息
         */
        for(Role role : user.getRoles()){
            System.out.println("UserProfile : "+role);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getType()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }




}