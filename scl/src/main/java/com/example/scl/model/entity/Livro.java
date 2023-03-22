package com.example.scl.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    private Long id;

    private String nome;

    private String nomeAutor;

    private String nomeEditora;

    private Integer nota;

    private Date dataLancamento;

    @ManyToOne
    private Genero genero;

    @ManyToOne
    private Editora editora;
}
