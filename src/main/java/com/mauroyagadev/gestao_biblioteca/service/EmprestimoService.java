package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioService usuarioService, LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> findById(Integer id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo save(int usuarioId, int livroId, LocalDate dataDevolucao, Emprestimo.StatusEmprestimo status) {
        Usuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Livro livro = livroService.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setStatus(status);

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo update(Integer id, Emprestimo emprestimo) {
        return emprestimoRepository.findById(id)
                .map(existingEmprestimo -> {
                    existingEmprestimo.setUsuario(emprestimo.getUsuario());
                    existingEmprestimo.setLivro(emprestimo.getLivro());
                    existingEmprestimo.setDataDevolucao(emprestimo.getDataDevolucao());
                    return emprestimoRepository.save(existingEmprestimo);
                })
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));
    }
    public void deleteById(Integer id) {
        emprestimoRepository.deleteById(id);
    }
}
