package com.mauroyagadev.gestao_biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emprestimo_id")
    private int id;

    @Column(name = "data_emprestimo", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    public enum StatusEmprestimo {
        EMPRESTADO, DEVOLVIDO, RESERVADO
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEmprestimo status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @PrePersist
    public void prePersist() {
        this.dataEmprestimo = LocalDate.now();
    }

}