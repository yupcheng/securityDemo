package com.yu.spring.web;

import com.yu.spring.entity.User;
import com.yu.spring.service.UserService;
import com.yu.spring.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public PageUtil<User> getAllUser()
    {
        return userService.findAll(null);

    }

    @RequestMapping(value = "/user_admin")
    public String index()
    {
        return "/sys/user_admin";

    }

}
