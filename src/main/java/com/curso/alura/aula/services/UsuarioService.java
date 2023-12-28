package com.curso.alura.aula.services;


import com.curso.alura.aula.dto.UsuarioDTO;
import com.curso.alura.aula.dto.UsuarioRequest;
import com.curso.alura.aula.models.Usuario;
import com.curso.alura.aula.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    public final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listAll(){
        List<Usuario> us = usuarioRepository.findAll();
        List<UsuarioDTO> uslist = us.stream()
                .map(usu -> new UsuarioDTO(usu.getId(), usu.getUsername(), usu.getPassword()))
                .collect(Collectors.toList());
        return uslist;
    }

    public String save(UsuarioRequest u){
        Usuario us = new Usuario();
        us.setUsername(u.getUsername());
        us.setPassword(u.getPassword());
        usuarioRepository.save(us);
        return "Usuario criado com sucesso!";
    }

    public void update(UsuarioRequest u, Long id){

        Usuario us = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        us.setUsername(u.getUsername());
        us.setPassword(u.getPassword());
        usuarioRepository.save(us);
    }

    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioDTO> getById(Long id){
        Optional<Usuario> us = usuarioRepository.findById(id);
        List<UsuarioDTO> uslist = us.stream()
                .map(usu -> new UsuarioDTO(usu.getId(), usu.getUsername(), usu.getPassword()))
                .collect(Collectors.toList());
        return uslist;
    }
}
