package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livrosRepository;
    @Autowired
    public LivroService(LivroRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    public List<Livro> findAll() {
        return livrosRepository.findAll();
    }

    public Optional<Livro> findById(Integer id) {
        return livrosRepository.findById(id);
    }
    public Livro save(Livro livro) {
        return livrosRepository.save(livro);
    }
public Livro update(Integer livro_id, Livro livro) {
        return livrosRepository.findById(livro_id)
                .map(existingLivro -> {
                    existingLivro.setTitulo(livro.getTitulo());
                    existingLivro.setAutor(livro.getAutor());
                    return livrosRepository.save(existingLivro);
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deleteById(Integer id) {
        livrosRepository.deleteById(id);
    }
}
