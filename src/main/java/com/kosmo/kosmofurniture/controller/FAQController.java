package com.kosmo.kosmofurniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FAQController {

    @GetMapping("/faq")
    public ModelAndView showNotice() {
        return new ModelAndView("faq");
    }
}
