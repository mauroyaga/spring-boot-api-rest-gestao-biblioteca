package com.mauroyagadev.gestao_biblioteca.service;

import com.mauroyagadev.gestao_biblioteca.entity.Emprestimo;
import com.mauroyagadev.gestao_biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    private final EmprestimoRepository emprestimoRepository;


    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> findById(Integer id) {
        return emprestimoRepository.findById(id);
    }

    public Emprestimo save(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public void deleteById(Integer id) {
        emprestimoRepository.deleteById(id);
    }
}
