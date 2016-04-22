package com.chart.controllers;

import com.chart.repositories.Chart;
import com.chart.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
        model.addAttribute("find", new Chart());
        model.addAttribute("charts", chartRepository.findAll());

        return "result";
    }

    @RequestMapping(value="/result", method= RequestMethod.POST)
    public String searchByName(@ModelAttribute("find") Chart c, Model model)
    {
        List<Chart> newOne = (List<Chart>)chartRepository.findByChartname(c.getChartname());
        if(newOne!= null)
            model.addAttribute("charts", newOne);
        System.out.println("WYNIK: " + newOne.toString());
        return "result";
    }

    @RequestMapping(value="/byInput")
    public String searchByInput(@ModelAttribute("find") Chart c, Model model) {
        List<Chart> newOne = (List<Chart>)chartRepository.findByInput(c.getInput());
        if(newOne!= null)
            model.addAttribute("charts", newOne);
        System.out.println("WYNIK: " + newOne.toString());
        return "result";
    }

}
