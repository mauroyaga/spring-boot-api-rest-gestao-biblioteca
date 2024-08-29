package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Livro;
import com.mauroyagadev.gestao_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 * Classe de serviço que contém a lógica de negócio para a entidade Livro.
 * Ela é responsável por intermediar as requisições feitas pelos controllers e
 * realizar as operações no banco de dados.
 */
@Service
public class LivroService {
    private final LivroRepository livrosRepository;

    // Construtor da classe que recebe o repositório de livros como parâmetro
    @Autowired
    public LivroService(LivroRepository livrosRepository) {
        this.livrosRepository = livrosRepository;
    }

    // Busca todos os livros
    public List<Livro> findAll() {
        return livrosRepository.findAll();
    }

    // Busca um livro pelo id
    public Optional<Livro> findById(Integer id) {
        return livrosRepository.findById(id);
    }

    // Salva um livro
    public Livro save(Livro livro) {
        return livrosRepository.save(livro);
    }

    /**
     * Método que atualiza um livro pelo id e retorna o livro atualizado.
     * A função lambda é aplicada ao valor se o valor estiver presente.
     * Se o valor não estiver presente, o método orElseThrow() é chamado e lança uma exceção.
     * */
    public Livro update(Integer livro_id, Livro livro) {
        return livrosRepository.findById(livro_id)
                .map(existingLivro -> {
                    existingLivro.setTitulo(livro.getTitulo());
                    existingLivro.setAutor(livro.getAutor());
                    return livrosRepository.save(existingLivro);
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    // Deleta um livro pelo id
    public void deleteById(Integer id) {
        livrosRepository.deleteById(id);
    }
}
