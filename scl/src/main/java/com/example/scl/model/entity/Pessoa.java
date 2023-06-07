package com.example.scl.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public abstract class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String nacionalidade;

    private LocalDate dataNascimento;



}

