package com.topschool.xm.controller.user;

import com.topschool.xm.exception.UserNameNotFoundException;
import com.topschool.xm.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 小强
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping("")
    public String loginPage() {
        return "/admin/login";
    }

    @PostMapping("")
    public String login(@RequestParam int uid, @RequestParam String password, Model model, HttpServletRequest request) throws UserNameNotFoundException, NoPermissionException {
        String username = partnerService.getUsername(uid, password);
        if (username != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return "redirect:/admin/";
        }
        model.addAttribute("uid", uid);
        model.addAttribute("password", password);
        model.addAttribute("error", "用户名密码不匹配");

        return "/admin/login";
    }
}
