package com.curso.alura.aula.services;

import com.curso.alura.aula.dto.TaskDTO;
import com.curso.alura.aula.dto.TaskRequest;
import com.curso.alura.aula.models.Task;
import com.curso.alura.aula.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    private final TaskRepository taskRepository;

    public HomeService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAll(){
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getDescription(), task.getUser().getId()))
                .collect(Collectors.toList());
        return taskDTOs;

    }

    public String createTask(TaskRequest taskRequest){
        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        taskRepository.save(task);
        return "Tarefa criada com sucesso!";

    }

    public void updateTask(TaskRequest taskRequest, Long id){
        Task taskDTO = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskDTO.setDescription(taskRequest.getDescription());
        taskRepository.save(taskDTO);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getTasksByUser(Long userId){
        List<Task> tasks = taskRepository.findByUsuario_Id(userId);
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(task -> new TaskDTO(task.getId(), task.getDescription(), task.getUser().getId()))
                .collect(Collectors.toList());
        return taskDTOs;
    }
}
