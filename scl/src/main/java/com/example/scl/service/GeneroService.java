package com.example.scl.service;

import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Genero;
import com.example.scl.model.entity.Livro;

import com.example.scl.model.repository.GenereoRepository;
import com.example.scl.model.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class GeneroService {
    private GenereoRepository repository;

    public GeneroService(GenereoRepository repository) {
        this.repository = repository;
    }

    public List<Genero> getGeneros() {
        return repository.findAll();
    }

    public Optional<Genero> getGeneroById(Long id) {
        return repository.findById(id);
    }


    @Transactional
    public Genero salvar(Genero genero) {
        validar(genero);
        return repository.save(genero);
    }

    @Transactional
    public void excluir(Genero genero) {
        Objects.requireNonNull(genero.getId());
        repository.delete(genero);
    }

    public void validar(Genero genero) {
        if (genero.getNome() == null) {
            throw new RegraNegocioException("O gênero deve possuir um nome!");
        }

    }
}