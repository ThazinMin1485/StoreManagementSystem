package com.store.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServerController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Java server!";
    }
}
