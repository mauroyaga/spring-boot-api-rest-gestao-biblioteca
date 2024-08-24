package com.mauroyagadev.gestao_biblioteca.repository;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
}
