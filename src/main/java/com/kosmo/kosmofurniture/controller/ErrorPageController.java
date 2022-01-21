package com.kosmo.kosmofurniture.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class ErrorPageController {

    @RequestMapping("/error/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        return "errors/500";
    }
}
