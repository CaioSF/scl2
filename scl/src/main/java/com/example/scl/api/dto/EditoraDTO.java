package com.example.scl.api.dto;


import com.example.scl.model.entity.Editora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraDTO {


    private Long id;
    private String nome;


    public static EditoraDTO create(Editora editora) {
        ModelMapper modelMapper = new ModelMapper();
        EditoraDTO dto = modelMapper.map(editora, EditoraDTO.class);
        dto.nome = editora.getNome();
        return dto;
    }


}
