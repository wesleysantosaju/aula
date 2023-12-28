package com.curso.alura.aula.dto;

public class TaskDTO {
    private Long id;
    private String description;
    private Long userId;

    public TaskDTO(Long id, String description, Long userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    // Construtores, getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
