package com.example.scl.api.dto.controller;

import com.example.scl.api.dto.AutorDTO;
import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Autor;
import com.example.scl.model.entity.Livro;
import com.example.scl.service.AutorService;
import com.example.scl.service.LivroService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers.converter;


@Controller
@RestController
@RequestMapping("/api/v1/autores")
@RequiredArgsConstructor

public class AutorController {
    private final AutorService service;
    private final LivroService livroService;


    @GetMapping()
    public ResponseEntity get() {
        List<Autor> autores = service.getAutores();
        return ResponseEntity.ok(autores.stream().map(AutorDTO::create).collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Autor> autor = service.getAutorById(id);
            if (!autor.isPresent()) {
                return new ResponseEntity("Autor não encontrado", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(autor.map(AutorDTO::create));
        }

    @PostMapping()
    public ResponseEntity post(AutorDTO dto) {
        try {
            Autor autor = converter(dto);
            Livro livro = livroService.salvar(autor.getLivro());
            autor.setLivro((livro));
            autor = service.salvar(autor);
            return new ResponseEntity(autor, HttpStatus.CREATED);
        } catch (RegraNegocioException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AutorDTO dto) {
        if (!service.getAutorById(id).isPresent()) {
            return new ResponseEntity("Autor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Autor autor = converter(dto);
            autor.setId(id);
            Livro livro = livroService.salvar(autor.getLivro());
            autor.setLivro(livro);
            service.salvar(autor);
            return ResponseEntity.ok(autor);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Autor> autor = service.getAutorById(id);
        if (!autor.isPresent()) {
            return new ResponseEntity("Autor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(autor.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Autor converter(AutorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Autor autor = modelMapper.map(dto, Autor.class);

        if (dto.getIdLivro() != null) {
            Optional<Livro> livro = livroService.getLivroById(dto.getIdLivro());
            if (!livro.isPresent()) {
                autor.setLivro(null);
            } else {
                autor.setLivro(livro.get());
            }
        }

        return autor;
    }

}

