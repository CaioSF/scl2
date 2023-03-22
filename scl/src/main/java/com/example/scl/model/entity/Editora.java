package com.example.scl.model.entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Editora {
    @Id
    private Long id;

    private String nome;
}
