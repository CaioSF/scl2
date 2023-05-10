package com.example.scl.service;


import com.example.scl.exception.RegraNegocioException;
import com.example.scl.model.entity.Livro;

import com.example.scl.model.repository.LivroRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


    @Service
    public class LivroService {
        private LivroRepository repository;

        public LivroService(LivroRepository repository) {
            this.repository = repository;
        }

        public List<Livro> getLivros() {
            return repository.findAll();
        }

        public Optional<Livro> getLivroById(Long id) {
            return repository.findById(id);
        }



        @Transactional
        public void excluir(Livro livro) {
            Objects.requireNonNull(livro.getId());
            repository.delete(livro);
        }

        @Transactional
        public Livro salvar(Livro livro) {
            validar(livro);
            return repository.save(livro);

        }

        public void validar (Livro livro) {
            if (livro.getNomeAutor() ==null) {
                throw new RegraNegocioException("Livro não encontrado");
            }
            if (livro.getNome() ==null || livro.getNome().trim().equals("")) {
                throw new RegraNegocioException("Nome Inválido");
            }
        }
}
