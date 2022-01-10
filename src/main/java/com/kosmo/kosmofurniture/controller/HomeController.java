package com.kosmo.kosmofurniture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping("/403error")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenError() {

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", HttpStatus.FORBIDDEN.value());
        mav.addObject("errorStatus", HttpStatus.FORBIDDEN);
        mav.addObject("errorMessage", "접근권한이 없습니다.");
        return mav;
    }
}
