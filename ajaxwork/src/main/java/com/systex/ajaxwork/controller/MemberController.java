package com.systex.ajaxwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.systex.ajaxwork.model.MemberModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

    // 顯示登入頁面
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("member", new MemberModel());
        return "login"; // 返回 login.jsp 頁面
    }

    // 處理登入請求
    @PostMapping("/login")
    public ModelAndView handleLogin(HttpServletRequest request, HttpSession session) {
        // 如果已經登入，重定向到首頁
        if (session.getAttribute("loggedInUser") != null) {
            return redirectToHomePage();
        }

        // 取得錯誤訊息並返回登入頁面
        return prepareLoginView(request);
    }

    // 顯示註冊頁面
    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "member", new MemberModel()); // 返回 register.jsp 頁面
    }

    // 處理註冊請求
    @PostMapping("/register")
    public ModelAndView handleRegister(HttpServletRequest request) {
        // 取得錯誤訊息
        Object error = request.getAttribute("error");

        // 如果有錯誤訊息，返回註冊頁面
        if (error != null) {
            return new ModelAndView("register", "member", new MemberModel()).addObject("error", error);
        }

        return new ModelAndView("redirect:/login"); // 註冊成功後重定向到登入頁面
    }

    // 處理登出請求
    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate(); // 刪除 session 中的用戶對象
        return "redirect:/login"; // 登出後重定向到登入頁面
    }

    // 重定向到首頁
    private ModelAndView redirectToHomePage() {
        return new ModelAndView("redirect:/index.jsp");
    }

    // 準備登入頁面的 ModelAndView
    private ModelAndView prepareLoginView(HttpServletRequest request) {
        Object error = request.getAttribute("error");
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("error", error);
        }
        return modelAndView;
    }
}
