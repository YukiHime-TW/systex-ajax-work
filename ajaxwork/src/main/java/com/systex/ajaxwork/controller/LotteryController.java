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
    public ModelAndView lotteryForm() {
        return new ModelAndView("lotteryForm", "lotteryForm", new LotteryForm());
    }

    @PostMapping("lotteryGenerate")
    public ModelAndView lotteryGenerate(@ModelAttribute LotteryForm form, Model model) {

        ArrayList<TreeSet<Integer>> list = new ArrayList<>();

        try{
            list = lotteryService.getLottery(form);
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("lotteryForm");
        }
        
        return new ModelAndView("lotteryResult", "lotteryForm", form)
                    .addObject("result", list);
    }
    
}
