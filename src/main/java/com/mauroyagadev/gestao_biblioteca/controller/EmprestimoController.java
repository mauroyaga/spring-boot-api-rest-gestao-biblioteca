package com.mauroyagadev.gestao_biblioteca.controller;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.service.EmprestimoService;
import com.mauroyagadev.gestao_biblioteca.service.LivroService;
import com.mauroyagadev.gestao_biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroService livroService;
    @GetMapping
    public List<Emprestimo> findAll() {
        return emprestimoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Integer id) {
        return emprestimoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Emprestimo save(@RequestBody Emprestimo emprestimo) {
        Usuario usuario = usuarioService.findById(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Livro livro = livroService.findById(emprestimo.getLivro().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        return emprestimoService.save(emprestimo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> update(@PathVariable Integer id, @RequestBody Emprestimo emprestimo) {
        Emprestimo updatedEmprestimo = emprestimoService.update(id, emprestimo);
        return ResponseEntity.ok(updatedEmprestimo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        emprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}