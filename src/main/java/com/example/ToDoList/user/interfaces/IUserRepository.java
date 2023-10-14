package com.example.ToDoList.user.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDoList.user.models.UserModel;


public interface IUserRepository extends JpaRepository<UserModel, UUID>{
       
}
