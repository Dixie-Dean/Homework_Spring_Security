package com.example.dixie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Greetings from the AppController!");
    }

    @GetMapping("/farewell")
    public ResponseEntity<String> tellFarewell() {
        return ResponseEntity.ok("Fare thee well, user! See you soon!");
    }
}