package com.example.scl.api.controller;


import com.example.scl.api.dto.AutorDTO;
import com.example.scl.api.dto.LivroDTO;
import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Autor;
import com.example.scl.model.entity.Livro;
import com.example.scl.service.AutorService;
import com.example.scl.service.GeneroService;
import com.example.scl.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/livros")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService service;

    private final GeneroService generoService;

    private final AutorService autorService;


    @GetMapping()

    public ResponseEntity get() {
        List<Livro> livros = service.getLivros();
        return ResponseEntity.ok(livros.stream().map(LivroDTO::create).collect(Collectors.toList()));

    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Livro> livro = service.getLivroById(id);
        if (!livro.isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(livro.map(LivroDTO::create));
    }

    @PostMapping()

    public ResponseEntity post(@RequestBody  LivroDTO dto) {
        try {
           Livro livro = converter(dto);




            livro = service.salvar(livro);
            return new ResponseEntity(livro, HttpStatus.CREATED);
        } catch (RegraNegocioException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id")  final Long id, @RequestBody LivroDTO dto) {
        if (!service.getLivroById(id).isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Livro livro = converter(dto);
            livro.setId(id);

            service.salvar(livro);
            return ResponseEntity.ok(livro);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Livro> livro = service.getLivroById(id);
        if (!livro.isPresent()) {
            return new ResponseEntity("Livro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(livro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    public Livro converter(LivroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Livro livro = modelMapper.map(dto, Livro.class);
        




        return livro;
    }
}
