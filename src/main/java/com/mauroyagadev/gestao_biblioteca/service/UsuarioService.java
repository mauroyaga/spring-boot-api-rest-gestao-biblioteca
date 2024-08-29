package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 * Classe de serviço que contém a lógica de negócio para a entidade Usuario.
 * Ela é responsável por intermediar as requisições feitas pelos controllers e
 * realizar as operações no banco de dados.
 * */
@Service
public class UsuarioService {
    // Injeção de dependência do repositório de usuários
    private final UsuarioRepository usuarioRepository;

    // Construtor da classe que recebe o repositório de usuários como parâmetro
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Busca todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    //Busca um usuário pelo id
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    //Salva um usuário
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Atualiza um usuário pelo id e retorna o usuário atualizado.
     * O método findById retorna um Optional, que é um container que pode ou não conter um valor.
     * Se o valor estiver presente, o método map() é chamado e a função lambda é aplicada ao valor.
     * Se o valor não estiver presente, o método orElseThrow() é chamado e lança uma exceção.
     * */
    public Usuario update(Integer id, Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNome(usuario.getNome());
                    existingUser.setEmail(usuario.getEmail());
                    return usuarioRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    //Deleta um usuário pelo id
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
