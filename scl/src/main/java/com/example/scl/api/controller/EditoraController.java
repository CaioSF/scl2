package com.example.scl.api.dto.controller;


import com.example.scl.api.dto.EditoraDTO;
import com.example.scl.api.dto.LivroDTO;
import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Editora;
import com.example.scl.model.entity.Livro;
import com.example.scl.service.EditoraService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/editoras")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EditoraController {

    private final EditoraService editoraService;

    @GetMapping()
    public ResponseEntity get() {
        List<Editora> editoras = editoraService.getEditoras();
        return ResponseEntity.ok(editoras.stream().map(EditoraDTO::create).collect(Collectors.toList()));

    }

    @PostMapping()
    public ResponseEntity post( @RequestBody EditoraDTO dto) {
        try {
            Editora editora = converter(dto);

            editora = editoraService.salvar(editora);
            return new ResponseEntity(editora, HttpStatus.CREATED);
        }catch (RegraNegocioException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    public Editora converter(EditoraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Editora editora= modelMapper.map(dto, Editora.class);
        editora.getId();



        return editora;
    }



}