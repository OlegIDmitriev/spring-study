package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWeb {
    @RequestMapping("/")
    public String sayHi() {
        return "Hello web!";
    }
}
