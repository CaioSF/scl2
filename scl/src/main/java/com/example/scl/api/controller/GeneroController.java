package com.example.scl.api.dto.controller;


import com.example.scl.api.dto.EditoraDTO;
import com.example.scl.api.dto.GeneroDTO;
import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Editora;
import com.example.scl.model.entity.Genero;
import com.example.scl.service.EditoraService;
import com.example.scl.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/generos")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Genero> generos = generoService.getGeneros();
        return ResponseEntity.ok(generos.stream().map(GeneroDTO::create).collect(Collectors.toList()));

    }

    @PostMapping()
    public ResponseEntity post( @RequestBody GeneroDTO dto) {
        try {
            Genero genero = converter(dto);

            genero = generoService.salvar(genero);
            return new ResponseEntity(genero, HttpStatus.CREATED);
        }catch (RegraNegocioException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    public Genero converter(GeneroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Genero genero= modelMapper.map(dto, Genero.class);
        return genero;
    }
}