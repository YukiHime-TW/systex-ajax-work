package com.systex.ajaxwork.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    // 處理 404 錯誤
    @RequestMapping("/error")
    public ModelAndView handleError(Model model) {
        return new ModelAndView("error")
        .addObject("errorMessage", "抱歉！您所請求的頁面不存在。");
    }

    // 處理其他例外
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return new ModelAndView("error")
                .addObject("errorMessage", "發生了一個錯誤，請稍後再試。")
                .addObject("exception", e);
    }
}
