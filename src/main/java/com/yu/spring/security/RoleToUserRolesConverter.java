package com.yu.spring.security;

import com.yu.spring.entity.Role;
import com.yu.spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Administrator on 2017/6/12.
 */
public class RoleToUserRolesConverter implements Converter<Object, Role> {

    @Autowired
    RoleService userProfileService;

    /*
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Role convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        Role role= userProfileService.findById(id);
        System.out.println("Profile : "+role);
        return role;
    }

	/*
	 * Gets UserProfile by type
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	/*
	public UserProfile convert(Object element) {
		String type = (String)element;
		UserProfile profile= userProfileService.findByType(type);
		System.out.println("Profile ... : "+profile);
		return profile;
	}
	*/

}
