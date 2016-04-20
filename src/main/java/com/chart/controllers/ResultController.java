package com.chart.controllers;

import com.chart.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ResultController {

    @Autowired
    ChartRepository chartRepository;

    @RequestMapping(value="/result")
    public String helloSubmit(Model model) {
        return "result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showAllPosts(Model model) {
        model.addAttribute("charts", chartRepository.findAll());
        System.out.println(chartRepository.findAll());
        return "result";
    }
}
