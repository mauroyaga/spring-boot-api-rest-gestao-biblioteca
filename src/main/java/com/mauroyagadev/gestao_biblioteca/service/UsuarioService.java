package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

//    public Usuario update(Integer id, Usuario usuarioAtualizado) {
//        return usuarioRepository.findById(id)
//                .map(usuario -> {
//                    usuario.setNome(usuarioAtualizado.getNome());
//                    usuario.setEmail(usuarioAtualizado.getEmail());
//                    usuario.setTelefone(usuarioAtualizado.getTelefone());
//                    return usuarioRepository.save(usuario);
//                })
//                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
//    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
