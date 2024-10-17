package com.systex.ajaxwork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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

    private final LotteryService lotteryService;

    private static final String VIEW_NAME = "lotteryForm";
    private static final String MODEL_NAME = "lotteryForm";

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }
    
    @GetMapping("input")
    public ModelAndView lotteryForm() {
        return new ModelAndView(VIEW_NAME, MODEL_NAME, new LotteryForm());
    }

    @PostMapping("lotteryGenerate")
    public ModelAndView lotteryGenerate(@ModelAttribute LotteryForm form, Model model) {

        List<TreeSet<Integer>> list = new ArrayList<>();

        try{
            list = lotteryService.getLottery(form);
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView(VIEW_NAME);
        }
        
        return new ModelAndView("lotteryResult", MODEL_NAME, form)
                    .addObject("result", list);
    }
    
}
