package com.topschool.xm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String loginPage() {
        return "/admin/login";
    }

    @PostMapping("")
    public String login(String username, String password, Model model) {
        if (username.equals("abcd")&&password.equals("123456")){
            return "admin/index";
        }
        model.addAttribute("username", username);
        model.addAttribute("password", password);

        return "/admin/login";
    }
}
