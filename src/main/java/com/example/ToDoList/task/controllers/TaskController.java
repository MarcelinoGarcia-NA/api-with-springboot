package com.example.ToDoList.task.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDoList.task.interfaces.ITaskRepository;
import com.example.ToDoList.task.models.TaskModel;
import com.example.ToDoList.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

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
    public ResponseEntity create(@RequestBody TaskModel task, HttpServletRequest request) {
        var idUser =  request.getAttribute("idUser");
        task.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(task.getStartAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de ínicio deve ser maior que a data atual");
        }
         if(task.getStartAt().isAfter(task.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data do fim da atividade deve ser maior que a data inicial");
        }
        var taskCreated = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }
      @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){  
        
        var task = this.taskRepository.findById(id).orElse(null);
      

        if(task == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Atividade não encontrada!");
        }
        var idUser = request.getAttribute("idUser");

        if(!task.getIdUser().equals(idUser)){
           return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para esta operação!");
        }

        Utils.copyNonNullProprieties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskUpdated);
    }
}
