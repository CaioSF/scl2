package com.example.scl.service;

import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Autor;
import com.example.scl.model.repository.AutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AutorService {
    private AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public List<Autor> getAutores() {
        return repository.findAll();
    }

    public Optional<Autor> getAutorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Autor salvar(Autor autor) {
        validar(autor);
        return repository.save(autor);
    }

    @Transactional
    public void excluir(Autor autor) {
        Objects.requireNonNull(autor.getId());
        repository.delete(autor);
    }

    public void validar(Autor autor) {
        if (autor.getLivro() ==null) {
            throw new RegraNegocioException("Autor não encontrado");
        }
        if (autor.getNome() ==null || autor.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome Inválido");
        }
    }
}

