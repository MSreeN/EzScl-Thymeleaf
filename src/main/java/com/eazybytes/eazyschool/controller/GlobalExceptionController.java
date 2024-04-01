package com.eazybytes.eazyschool.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(HolidayNotFound.class)
    public ModelAndView holidayException(HolidayNotFound holidayNotFound){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg", holidayNotFound.getMessage());
        return modelAndView;
    }
}
