package com.curso.alura.aula.controllers;

import com.curso.alura.aula.dto.UsuarioDTO;
import com.curso.alura.aula.dto.UsuarioRequest;
import com.curso.alura.aula.models.Task;
import com.curso.alura.aula.models.Usuario;
import com.curso.alura.aula.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listAll() {
        List<Usuario> us = usuarioRepository.findAll();
        List<UsuarioDTO> uslist = us.stream()
                .map(usu -> new UsuarioDTO(usu.getId(), usu.getUsername(), usu.getPassword()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(uslist, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity createUser(@RequestBody UsuarioRequest ur) {
        // Criar a tarefa
        Usuario usuario = new Usuario();
        usuario.setUsername(ur.getUsername());
        usuario.setPassword(ur.getPassword());

        // Salvar a tarefa no repositório
        usuarioRepository.save(usuario);

        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuario criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity updateUser(@RequestBody UsuarioRequest ur, @PathVariable Long id) {
        //atualizar o usuario
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setUsername(ur.getUsername());
        usuario.setPassword(ur.getPassword());

        // Salvar o usuário atualizado no repositório
        usuarioRepository.save(usuario);

        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
        }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        //deletar o usuario
        usuarioRepository.deleteById(id);
        // Retornar resposta de sucesso
        return new ResponseEntity<>("Usuario deletado com sucesso!", HttpStatus.OK);
    }
}
