package com.eazybytes.eazyschool.controller;


import org.springframework.web.bind.annotation.ExceptionHandler;


public class HolidayNotFound extends RuntimeException{
    public HolidayNotFound(String message){
        super(message);
    }
}
