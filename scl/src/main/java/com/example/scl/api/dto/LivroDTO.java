package com.example.scl.api.dto;


import com.example.scl.model.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    private String nome;

    private String nomeAutor;

    private String nomeEditora;

    private Integer nota;

    private Date dataLancamento;

    public static LivroDTO create(Livro livro) {
        ModelMapper modelMapper = new ModelMapper();
        LivroDTO dto = modelMapper.map(livro, LivroDTO.class);
        dto.nome = livro.getNome();
        dto.nomeAutor = livro.getNomeAutor();
        dto.nomeEditora = livro.getNomeEditora();
        dto.nota = livro.getNota();
        dto.dataLancamento = livro.getDataLancamento();
        return dto;
    }
}
