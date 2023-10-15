package com.example.ToDoList.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDoList.task.interfaces.ITaskRepository;
import com.example.ToDoList.task.models.TaskModel;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    ITaskRepository taskRepository;

    @GetMapping("/")
    public String get() {
        return "olá";
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel task) {
        var taskCreated = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }
}
