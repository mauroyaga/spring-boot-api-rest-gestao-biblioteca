package com.mauroyagadev.gestao_biblioteca.controller;

import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import com.mauroyagadev.gestao_biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controlador REST para a entidade Usuário
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    // Injeção de dependência do serviço de usuário
    private final UsuarioService usuarioService;

    // Injeção de dependência do serviço de usuário
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método para buscar todos os usuários
    @GetMapping
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    // Método para buscar um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para salvar um usuário
    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    //  Método para atualizar um usuário por ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Usuario updatedUser = usuarioService.update(id, usuario);
        return ResponseEntity.ok(updatedUser);
    }

    // Método para deletar um usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}