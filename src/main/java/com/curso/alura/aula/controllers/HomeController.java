package com.curso.alura.aula.controllers;

import com.curso.alura.aula.dto.TaskDTO;
import com.curso.alura.aula.dto.TaskRequest;
import com.curso.alura.aula.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class HomeController {

    @Autowired
    private final HomeService homeService;


    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAll() {
        return new ResponseEntity<>(homeService.getAll(), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest) {
        homeService.createTask(taskRequest);
        return new ResponseEntity<>("Tarefa criada com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByUser(@PathVariable Long id) {
        return new ResponseEntity<>(homeService.getTasksByUser(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
        homeService.updateTask(taskRequest, id);
        return new ResponseEntity<>("Tarefa atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        homeService.deleteTask(id);
        return new ResponseEntity<>("Tarefa deletada com sucesso!", HttpStatus.OK);
    }
}
