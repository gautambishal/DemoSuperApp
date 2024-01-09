package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneController {

    @GetMapping("/ping")
    public String pingApi(){
        return "Alive!!";
    }
}
