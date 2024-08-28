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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public Emprestimo save(@RequestBody Map<String, Object> body) {
        int usuarioId = (int) body.get("usuarioId");
        int livroId = (int) body.get("livroId");
        LocalDate dataDevolucao = LocalDate.parse((String) body.get("dataDevolucao"));
        Emprestimo.StatusEmprestimo status = Emprestimo.StatusEmprestimo.valueOf((String) body.get("status"));
        return emprestimoService.save(usuarioId, livroId, dataDevolucao, status);
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