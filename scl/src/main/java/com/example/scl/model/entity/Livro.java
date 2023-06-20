package com.example.scl.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String genero;

    private Integer numeroPaginas;

    private LocalDate dataLancamento;



    @ManyToOne
    private Editora editora;

    @ManyToOne
    private Autor autor;
}
