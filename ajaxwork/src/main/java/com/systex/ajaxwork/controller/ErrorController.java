package com.systex.ajaxwork.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    private static final String ERROR_VIEW = "error";
    private static final String ERROR_MESSAGE_KEY = "errorMessage";
    private static final String DEFAULT_NOT_FOUND_MESSAGE = "抱歉！您所請求的頁面不存在。";
    private static final String DEFAULT_ERROR_MESSAGE = "發生了一個錯誤，請稍後再試。";

    // 處理 404 錯誤
    @RequestMapping("/error")
    public ModelAndView handleError(Model model) {
        return createErrorModelAndView(DEFAULT_NOT_FOUND_MESSAGE);
    }

    // 處理其他例外
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        return createErrorModelAndView(DEFAULT_ERROR_MESSAGE)
                .addObject("exception", e);
    }

    private ModelAndView createErrorModelAndView(String message) {
        return new ModelAndView(ERROR_VIEW)
                .addObject(ERROR_MESSAGE_KEY, message);
    }
}
