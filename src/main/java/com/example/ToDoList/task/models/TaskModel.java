package com.example.ToDoList.task.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(length = 255)
    private String description;

    @Column(length = 50)
    private String title;

    private String priority;
    
    @CreationTimestamp
    private LocalDateTime startAt;

    @CreationTimestamp
    private LocalDateTime endAt;

    private UUID idUser;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("Título extenso, com 50 caracteres");
        }
        this.title = title;
    }
}
