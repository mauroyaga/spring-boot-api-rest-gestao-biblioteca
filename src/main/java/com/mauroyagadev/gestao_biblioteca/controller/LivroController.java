package com.mauroyagadev.gestao_biblioteca.controller;

import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controlador REST para a entidade Livro
@RestController
@RequestMapping("/livro")
public class LivroController {

    // Injeção de dependência do serviço de livro
    private final LivroService livroService;

    // Injeção de dependência do serviço de livro
    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Método para buscar todos os livros
    @GetMapping
    public List<Livro> findAll() {
        return livroService.findAll();
    }

    // Método para buscar um livro por ID
    @PostMapping
    public Livro save(@RequestBody Livro livro) {
        return livroService.save(livro);
    }

    // Método para buscar um livro por ID
    @PutMapping
    public ResponseEntity<Livro> update(@RequestParam Integer id, @RequestBody Livro livro) {
        Livro updatedLivro = livroService.update(id, livro);
        return ResponseEntity.ok(updatedLivro);
    }

    // Método para buscar um livro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
