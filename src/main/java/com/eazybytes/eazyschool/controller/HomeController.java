package com.eazybytes.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value={"", "/", "home"})
    public String displayHomePage() {
        return "home.html";
    }

    @GetMapping("/courses")
    public String displayCourses(){
        return "courses.html";
    }

    @GetMapping("/about")
    public String displayAbout(){
        return "about.html";
    }

}
