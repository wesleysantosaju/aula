package com.curso.alura.aula.controllers;

import com.curso.alura.aula.dto.UsuarioDTO;
import com.curso.alura.aula.dto.UsuarioRequest;
import com.curso.alura.aula.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listAll() {
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<UsuarioDTO>> getById(@PathVariable Long id) {
        // Buscar o usuário pelo ID
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity createUser(@RequestBody UsuarioRequest ur) {
        //criar o usuario
        userService.save(ur);
        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuario criado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity updateUser(@RequestBody UsuarioRequest ur, @PathVariable Long id) {
        //atualizar o usuario
        userService.update(ur, id);
        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuario atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        //deletar o usuario
        userService.delete(id);
        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuario deletado com sucesso!", HttpStatus.OK);
    }
}
