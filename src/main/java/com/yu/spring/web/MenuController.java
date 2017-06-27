package com.yu.spring.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yu.spring.entity.Menu;
import com.yu.spring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/25.
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String index()
    {
        return null;
    }

    @ModelAttribute(value = "jsonTree")
    public String menuJsonTree()
    {
        JSONArray json = new JSONArray();
        json.addAll(menuService.queryJsonTreeAll());
        return json.toJSONString();

    }

   @ModelAttribute("menuList")
    public String queryAll()
    {
        JSONArray json = new JSONArray();
        json.addAll(menuService.queryAll());
        return json.toJSONString();
    }
}
