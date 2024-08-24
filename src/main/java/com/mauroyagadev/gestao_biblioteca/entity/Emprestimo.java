package com.mauroyagadev.gestao_biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @Column(name = "data_emprestimo", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    // Enum para representar os status do empréstimo
    public enum StatusEmprestimo {
        EMPRESTADO, DEVOLVIDO, RESERVADO
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}