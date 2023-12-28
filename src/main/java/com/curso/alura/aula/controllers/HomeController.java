package com.curso.alura.aula.controllers;

import com.curso.alura.aula.dto.TaskDTO;
import com.curso.alura.aula.dto.TaskRequest;
import com.curso.alura.aula.models.Task;
import com.curso.alura.aula.models.Usuario;
import com.curso.alura.aula.repositories.TaskRepository;
import com.curso.alura.aula.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/")
public class HomeController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public HomeController(TaskRepository taskRepository, UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDTO>> getAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getDescription(), task.getUser().getId()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity createTask(@RequestBody TaskRequest taskRequest) {
        // Verificar se o ID do usuário é nulo
        Long userId = taskRequest.getUserId();
        if (userId == null) {
            return new ResponseEntity<>("ID do usuário é nulo", HttpStatus.BAD_REQUEST);
        }

        // Buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Criar a tarefa
        Task task = new Task();
        task.setUser(usuario);
        task.setDescription(taskRequest.getDescription());

        // Salvar a tarefa no repositório
        taskRepository.save(task);

        // Retornar resposta de sucesso
        return new ResponseEntity<>("Tarefa criada com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskDTO> getId(@PathVariable Long id) {
        Task taskDTO = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return new ResponseEntity<>(new TaskDTO(taskDTO.getId(), taskDTO.getDescription(), taskDTO.getUser().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        Task taskDTO = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskRepository.deleteById(id);
        return new ResponseEntity<>("Tarefa excluída com sucesso", HttpStatus.OK);
    }
}
