package com.mauroyagadev.gestao_biblioteca.controller;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import com.mauroyagadev.gestao_biblioteca.repository.EmprestimoRepository;
import com.mauroyagadev.gestao_biblioteca.service.EmprestimoService;
import com.mauroyagadev.gestao_biblioteca.service.LivroService;
import com.mauroyagadev.gestao_biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


// Controlador REST para a entidade Emprestimo
@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    // Injeção de dependência do serviço de empréstimo
    private final EmprestimoService emprestimoService;

    // Injeção de dependência do serviço de usuário
    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // Injeção de dependência do serviço de usuário
    @Autowired
    private UsuarioService usuarioService;

    // Injeção de dependência do serviço de livro
    @Autowired
    private LivroService livroService;

    // Injeção de dependência do repositório de empréstimo
    @Autowired
    private EmprestimoRepository EmprestimoRepository;

    //  Método para buscar todos os empréstimos
    @GetMapping
    public List<Emprestimo> findAll() {
        return emprestimoService.findAll();
    }

    /// Método para buscar um empréstimo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> findById(@PathVariable Integer id) {
        return emprestimoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para salvar um empréstimo
    @PostMapping
    public Emprestimo save(@RequestBody Map<String, Object> body) {
        int usuario_id = (int) body.get("usuario_id"); // Converte o ID do usuário para inteiro
        int livro_id = (int) body.get("livro_id"); // Converte o ID do livro para inteiro
        LocalDate dataDevolucao = LocalDate.parse((String) body.get("dataDevolucao"));
        Emprestimo.StatusEmprestimo status = Emprestimo.StatusEmprestimo.valueOf((String) body.get("status"));
        return emprestimoService.save(usuario_id, livro_id, dataDevolucao, status);
    }

    // Método para atualizar um empréstimo
    @PutMapping("/{id}")
    public Emprestimo update(Integer id, Emprestimo emprestimo) {
        return EmprestimoRepository.findById(id)
                .map(existingEmprestimo -> {
                    existingEmprestimo.setUsuario(emprestimo.getUsuario());
                    existingEmprestimo.setLivro(emprestimo.getLivro());
                    existingEmprestimo.setDataDevolucao(emprestimo.getDataDevolucao());
                    existingEmprestimo.setStatus(emprestimo.getStatus());
                    return EmprestimoRepository.save(existingEmprestimo);
                })
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));
    }

    // Método para deletar um empréstimo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        emprestimoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}