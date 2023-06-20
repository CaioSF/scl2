package com.example.scl.api.controller;


import com.example.scl.api.dto.AutorDTO;
import com.example.scl.api.dto.EditoraDTO;
import com.example.scl.api.dto.LivroDTO;
import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Autor;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/editoras")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EditoraController {

    private final EditoraService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Editora> editoras = service.getEditoras();
        return ResponseEntity.ok(editoras.stream().map(EditoraDTO::create).collect(Collectors.toList()));

    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Editora> editora = service.getEditoraById(id);
        if (!editora.isPresent()) {
            return new ResponseEntity("Autor não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(editora.map(EditoraDTO::create));
    }

    @PostMapping()
    public ResponseEntity post( @RequestBody EditoraDTO dto) {
        try {
            Editora editora = converter(dto);

            editora = service.salvar(editora);
            return new ResponseEntity(editora, HttpStatus.CREATED);
        }catch (RegraNegocioException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id")  final Long id, @RequestBody final EditoraDTO dto) {
        if (!service.getEditoraById(id).isPresent()) {
            return new ResponseEntity("Editora não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Editora editora = converter(dto);
            editora.setId(id);

            service.salvar(editora);
            return ResponseEntity.ok(editora);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Editora> editora = service.getEditoraById(id);
        if (!editora.isPresent()) {
            return new ResponseEntity("Editora não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(editora.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    public Editora converter(EditoraDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Editora editora = modelMapper.map(dto, Editora.class);





        return editora;
    }



}