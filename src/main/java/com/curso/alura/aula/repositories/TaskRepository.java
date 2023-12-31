package com.curso.alura.aula.repositories;


import com.curso.alura.aula.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUsuario_Id(Long id);

}
