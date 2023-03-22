package com.example.scl.api.dto;

import com.example.scl.model.entity.Autor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AutorDTO {
    private Long id;
    private String nome;

    private String email;


    public static AutorDTO create(Autor autor) {
        ModelMapper modelMapper = new ModelMapper();
        AutorDTO dto = modelMapper.map(autor, AutorDTO.class);
        dto.nome = autor.getNome();
        dto.email = autor.getEmail();
        return dto;
    }
}
