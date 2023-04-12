package com.example.scl.service;

import com.example.scl.model.entity.Genero;
import com.example.scl.model.entity.Livro;

import com.example.scl.model.repository.GenereoRepository;
import com.example.scl.model.repository.LivroRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GeneroService {
    private GenereoRepository repository;

    public GeneroService (GenereoRepository repository) {
        this.repository = repository;
    }

    public List<Genero> getGeneros() {
        return repository.findAll();
    }

    public Optional<Genero> getGeneroById(Long id) {
        return repository.findById(id);
    }



    @Transactional
    public void excluir(Genero genero) {
        Objects.requireNonNull(genero.getId());
        repository.delete(genero);
    }
}
