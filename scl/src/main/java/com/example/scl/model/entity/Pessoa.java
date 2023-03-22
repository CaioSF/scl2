package com.example.scl.model.entity;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public abstract class Pessoa {
    @Id

    private Long id;
    private String nome;
    private Date dataNascimento;



}
