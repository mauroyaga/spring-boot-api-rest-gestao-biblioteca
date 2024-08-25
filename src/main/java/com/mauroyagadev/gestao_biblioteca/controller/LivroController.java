package com.mauroyagadev.gestao_biblioteca.controller;

import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {
    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
    @GetMapping
    public List<Livro> findAll() {
        return livroService.findAll();
    }
    @PostMapping
    public Livro save(@RequestBody Livro livro) {
        return livroService.save(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
