package com.example.scl.api.dto;


import com.example.scl.model.entity.Autor;
import com.example.scl.model.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {


    @Id
    private Long id;
    private String nome;





    private LocalDate dataLancamento;

    private Integer numeroPaginas;

    private Long idAutor;

    private Long idEditora;



    public static LivroDTO create(Livro livro) {
        ModelMapper modelMapper = new ModelMapper();
        LivroDTO dto = modelMapper.map(livro, LivroDTO.class);
        dto.nome = livro.getNome();
        dto.dataLancamento = livro.getDataLancamento();
        dto.numeroPaginas = livro.getNumeroPaginas();


        return dto;
    }
}
