package com.example.patient.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/patient")
public class TestController {

  @GetMapping("/hello")
  public String sayHello(){
    return "hello patient";
  }
  
}
