package com.yu.spring.web;

import com.yu.spring.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ModelAttribute("menuList")
    public List<Menu> menuList()
    {
        List<Menu> menuList = new ArrayList<>();
        Menu m = new Menu();
        m.setId(123);
        m.setMaker("132");
        m.setTitle("test菜单");

        Menu cm = new Menu();
        cm.setTitle("test子菜单");
        cm.setId(456);
        cm.setMaker("456");
        cm.setUrl("/sys/user/index");
        Set<Menu> set = new HashSet<Menu>();
        set.add(cm);
        m.setChildren(set);
        menuList.add(m);
        return menuList;
    }

}
