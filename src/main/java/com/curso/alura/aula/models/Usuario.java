package com.curso.alura.aula.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {

    public static final String TABLE_NAME = "user";

}
