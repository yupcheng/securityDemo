package com.yu.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        return null;
    }
}
