package com.example.soignant.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/soignant")
public class TestController {

  @GetMapping("/hello")
  public String sayHello() {
      return "hello soignant";
  }
  
}
