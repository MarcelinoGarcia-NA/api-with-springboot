package com.example.ToDoList.task.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDoList.task.models.TaskModel;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    
}
