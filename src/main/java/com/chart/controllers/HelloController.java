package com.chart.controllers;

import com.chart.generator.ChartGenerator;
import com.chart.repositories.Chart;
import com.chart.repositories.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @Autowired
    ChartRepository chartRepository;

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public String helloSubmit(Model model) {

        model.addAttribute("newchart", new Chart());
        return "add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String helloSubmit(@ModelAttribute("newchart") Chart c) {
        if(c.getChartname().length() > 0 && c.getInput().length() > 0)
        {
            ChartGenerator chartGenerator = new ChartGenerator();
            String s = chartGenerator.dataToString(c.getInput());
            if(s==null)
                return "redirect:add";
            c.setResult(s);
            chartRepository.save(c);
            return "redirect:result";
        }
            return "redirect:add";
    }
}
