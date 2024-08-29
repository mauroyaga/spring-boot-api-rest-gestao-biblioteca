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


/**
 * Classe de serviço que contém a lógica de negócio para a entidade Emprestimo.
 * Ela é responsável por intermediar as requisições feitas pelos controllers e
 * realizar as operações no banco de dados.
 * */
@Service
public class EmprestimoService {
    // Injeção de dependência do repositório de empréstimos
    @Autowired
    private final EmprestimoRepository emprestimoRepository;

    // Injeção de dependência do serviço de usuários
    private final UsuarioService usuarioService;

    // Injeção de dependência do serviço de livros
    private final LivroService livroService;

    /**
     * Construtor da classe que recebe o repositório de empréstimos,
     * o serviço de usuários e o serviço de livros como parâmetros
     * */
    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioService usuarioService, LivroService livroService) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioService = usuarioService;
        this.livroService = livroService;
    }

    // Busca todos os empréstimos
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    // Busca um empréstimo pelo id
    public Optional<Emprestimo> findById(Integer id) {
        return emprestimoRepository.findById(id);
    }

    // Salva um empréstimo
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

    /**
     * Método que atualiza um empréstimo pelo id e retorna o empréstimo atualizado.
     * Ele recebe o id do empréstimo e o objeto empréstimo como parâmetros.
     * A função lambda é aplicada ao valor se o valor estiver presente.
     * */
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
    // Deleta um empréstimo pelo id
    public void deleteById(Integer id) {
        emprestimoRepository.deleteById(id);
    }
}
