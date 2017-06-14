//package com.yu.spring.web;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by Administrator on 2017/6/7.
// * security 学习和代码实现
// */
//@Controller
////@RequestMapping("/hello")
//public class HelloController
//{
//
//    /**
//     * 网站主页，没有访问限制
//     * @return
//     */
//    @RequestMapping(value = { "/","/welcome" }, method = RequestMethod.GET)
//    public ModelAndView welcomePage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Hello World");
//        model.addObject("message", "This is welcome page!");
//        model.setViewName("hello");
//        return model;
//
//    }
//
//    /**
//     * admin用户主页
//     * @param modelAndView
//     * @return
//     */
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public ModelAndView adminPage(ModelAndView modelAndView) {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Hello World");
//        model.addObject("message", "This is protected page!");
//        model.setViewName("admin");
//
//        return model;
//
//    }
//
//    /**
//     * 用户登陆，登陆异常，退出登陆处理
//     * @param error
//     * @param logout
//     * @return
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login(
//            @RequestParam(value = "error", required = false) String error,
//            @RequestParam(value = "logout", required = false) String logout) {
//
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//
//    }
//
//    /**
//     * 自定义用户退出登录
//     * security ->/j_spring_security_logout
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            /**
//             *
//             HTTP的会话失效，那么解除绑定到它的任何对象；
//             将删除 SecurityContext 的身份验证，以防止并发请求的问题；
//             显式地清除当前线程上下文值；
//
//             */
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//            //重定向到login?longout
//        return "redirect:/login?logout";
//        //You can redirect wherever you want, but generally it's a good practice to show login screen again.
//    }
//
//
//}
