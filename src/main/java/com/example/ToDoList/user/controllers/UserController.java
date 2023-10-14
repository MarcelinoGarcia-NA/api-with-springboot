package com.example.ToDoList.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping("/")
    public ResponseEntity<String> create(){
        String hello = "olá mundo!";
        return ResponseEntity.status(HttpStatus.CREATED).body(hello);
    }
}
