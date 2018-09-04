package com.medical.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.medical.dao.AdminDao;
import com.medical.domain.Admin;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private AdminDao admindao;

    @GetMapping("/login")
    public ModelAndView page1() {
        return new ModelAndView("login");
    }

    /**
     * 登录
     * 
     * @param admin
     * @param model
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    public ModelAndView loginPost(Admin admin, Model model, HttpSession httpSession) {
        Admin adminRes = admindao.findByNameAndPassword(admin);
        if (adminRes != null) {
            httpSession.setAttribute("admin", adminRes);
            return new ModelAndView("redirect:dashboard");
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return new ModelAndView("login");
        }
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
