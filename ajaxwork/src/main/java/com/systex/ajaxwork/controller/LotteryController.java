package com.systex.ajaxwork.controller;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.systex.ajaxwork.model.LotteryForm;
import com.systex.ajaxwork.service.LotteryService;

@Controller
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @GetMapping("input")
    public ModelAndView showLotteryForm() {
        return createModelAndView("lotteryForm", new LotteryForm());
    }

    @PostMapping("lotteryGenerate")
    public ModelAndView generateLottery(@ModelAttribute LotteryForm form, Model model) {
        ArrayList<TreeSet<Integer>> results = generateLotteryResults(form, model);
        if (results == null) {
            return createModelAndView("lotteryForm", null); // 返回表單視圖
        }
        return createResultView(form, results);
    }

    private ArrayList<TreeSet<Integer>> generateLotteryResults(LotteryForm form, Model model) {
        try {
            return lotteryService.getLottery(form);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return null; // 發生錯誤時返回 null
        }
    }

    private ModelAndView createModelAndView(String viewName, LotteryForm form) {
        return new ModelAndView(viewName, "lotteryForm", form);
    }

    private ModelAndView createResultView(LotteryForm form, ArrayList<TreeSet<Integer>> results) {
        return createModelAndView("lotteryResult", form).addObject("result", results);
    }
}
