package com.example.scl.api.dto;

import com.example.scl.model.entity.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class GeneroDTO {
    private String nome;


    public static GeneroDTO create(Genero genero) {
        ModelMapper modelMapper = new ModelMapper();
        GeneroDTO dto = modelMapper.map(genero, GeneroDTO.class);
        dto.nome = genero.getNome();
        return dto;
    }
}
