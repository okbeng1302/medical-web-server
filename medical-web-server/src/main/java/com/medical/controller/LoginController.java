package com.medical.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.medical.dao.AdminDao;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private AdminDao admindao;

    @GetMapping("/login")
    public ModelAndView page1() {
        return new ModelAndView("login");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, Model model, Map<Object, Object> map) {
        // 登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        logger.info(UnknownAccountException.class.getName());
        if (exception != null) {
            if (StringUtils.equals(UnknownAccountException.class.getName(), exception)) {
                msg = "账号不存在";
            } else if (StringUtils.equals(IncorrectCredentialsException.class.getName(), exception)) {
                msg = "密码不正确";
            } else {
                msg = "未知错误";
            }
            model.addAttribute("error", msg);
            return "/login";
        }
        return "/dashboard";
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        return new ModelAndView("register");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(Model model) {
        return new ModelAndView("dashboard");
    }
}
