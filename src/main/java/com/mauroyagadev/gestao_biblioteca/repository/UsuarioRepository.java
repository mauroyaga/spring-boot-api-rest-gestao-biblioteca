package com.mauroyagadev.gestao_biblioteca.repository;

import com.mauroyagadev.gestao_biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
