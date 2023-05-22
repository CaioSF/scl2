package com.example.scl.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;


}
