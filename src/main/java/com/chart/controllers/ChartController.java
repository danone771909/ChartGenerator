package com.chart.controllers;

import com.chart.image.ImageToBase64;
import com.chart.image.TextToGraphic;
import com.chart.repositories.Chart;
import com.chart.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Daniel on 2016-04-19.
 */
@Controller
public class ChartController {
    @Autowired
    ChartRepository chartRepository;

    @RequestMapping(value="/chart/{id}")
    public ModelAndView showChart(@PathVariable("id") Long id, Model model) {
        Chart chart = chartRepository.findOne(id);
        model.addAttribute("c", chart);
        ModelAndView mav = new ModelAndView("chart");
        return mav;
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteChart(@PathVariable("id") Long id, Model model) {
        chartRepository.delete(id);
        model.addAttribute("charts", chartRepository.findAll());
        model.addAttribute("find", new Chart());
        return "result";
    }

    @RequestMapping(value="/image/{id}")
    public String showImage(@PathVariable("id") Long id, Model model)
    {
        Chart chart = chartRepository.findOne(id);

        ImageToBase64 imageToBase64 = new ImageToBase64();
        TextToGraphic textToGraphic = new TextToGraphic();

        String result = imageToBase64.encodeToString(textToGraphic.textToImage(chart.getResult()));
        model.addAttribute("image", result);
        model.addAttribute("c", chart);

        return "image";
    }

}
