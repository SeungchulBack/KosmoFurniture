package com.kosmo.kosmofurniture.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity home() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("API Running...");
    }
    @GetMapping("/jsphome")
    public ModelAndView jspHome() {
        return new ModelAndView("index");
    }
}
