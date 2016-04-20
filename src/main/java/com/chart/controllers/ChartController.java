package com.chart.controllers;

import com.chart.image.TextToGraphic;
import com.chart.repositories.Chart;
import com.chart.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

/**
 * Created by Daniel on 2016-04-19.
 */
@Controller
public class ChartController {
    @Autowired
    ChartRepository chartRepository;

    @RequestMapping(value="/chart/{id}")
    public ModelAndView showChart(@PathVariable("id") Long id, Model model) {
        System.out.println("Jestem tu");
        Chart chart = chartRepository.findOne(id);
        model.addAttribute("c", chart);
        TextToGraphic textToGraphic = new TextToGraphic(chart.getResult());
        ModelAndView mav = new ModelAndView("chart");
        return mav;
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteChart(@PathVariable("id") Long id, Model model) {
        chartRepository.delete(id);
        model.addAttribute("charts", chartRepository.findAll());
        return "result";
    }

}
