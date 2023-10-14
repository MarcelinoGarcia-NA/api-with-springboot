package com.example.ToDoList.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ToDoList.user.interfaces.IUserRepository;

import com.example.ToDoList.user.models.UserModel;

@RestController
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;
    
    @GetMapping("/")
    public ResponseEntity<String> initialAPI(){
        String hello = "API: To do list";
        return ResponseEntity.status(HttpStatus.CREATED).body(hello);
    }
    
    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
    
}
