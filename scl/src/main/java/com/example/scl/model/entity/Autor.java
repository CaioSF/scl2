package com.example.scl.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.persistence.ManyToOne;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor extends Pessoa {


    private String email;
    @Id
    private Long id;
    @ManyToOne
    private Livro livro;

}
