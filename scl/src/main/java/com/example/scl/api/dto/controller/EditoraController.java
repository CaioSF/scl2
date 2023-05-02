package com.example.scl.api.dto.controller;


import com.example.scl.api.dto.EditoraDTO;
import com.example.scl.model.entity.Editora;
import com.example.scl.service.EditoraService;
import com.example.scl.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/editoras")
@RequiredArgsConstructor

public class EditoraController {

    private final EditoraService service;
    private final LivroService livroService;

    @GetMapping()
    public ResponseEntity get() {
        List<Editora> editoras = service.getEditoras();
        return ResponseEntity.ok(editoras.stream().map(EditoraDTO::create).collect(Collectors.toList()));
    }

