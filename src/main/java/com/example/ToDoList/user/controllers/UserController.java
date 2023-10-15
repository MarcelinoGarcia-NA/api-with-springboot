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

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<String> initialAPI() {
        String hello = "API: To do list";
        return ResponseEntity.status(HttpStatus.CREATED).body(hello);
    }

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserModel user) {
        var userBD = this.userRepository.findByName(user.getName());
        if (userBD != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usúario já existente!");
        }
        var passwordHashred = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHashred);
        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
