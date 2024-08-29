package com.mauroyagadev.gestao_biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Classe que representa a entidade Emprestimo
 * Ela contém os atributos que representam as colunas da tabela emprestimo no banco de dados
 * Além disso, possui os relacionamentos com as entidades Usuario e Livro
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emprestimo_id")
    private int emprestimo_id;

    //Data do emprestimo é definida como a data atual
    @Column(name = "data_emprestimo", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    //Enum que representa o status do emprestimo

    public enum StatusEmprestimo {
        EMPRESTADO, DEVOLVIDO, RESERVADO
    }

    //Define os valores possíveis para o enum de status
    @Enumerated(EnumType.STRING) //Salva o valor do enum como string no banco de dados
    @Column(name = "status")
    private StatusEmprestimo status;

    @JsonBackReference(value = "usuario-emprestimo") //Evita recursão infinita da serialização
    @ManyToOne //Define o relacionamento muitos para um com a entidade Usuario
    @JoinColumn(name = "usuario_id")  //Define a coluna que será usada como chave estrangeira
    private Usuario usuario;

    //Define o relacionamento muitos para um com a entidade Livro
    @JsonBackReference(value = "livro-emprestimo") //Evita recursão infinita da serialização
    @ManyToOne //Define o relacionamento muitos para um com a entidade Livro
    @JoinColumn(name = "livro_id") //Define a coluna que será usada como chave estrangeira
    private Livro livro;

    //Define o comportamento que será executado antes de persistir o objeto no banco de dados
    @PrePersist
    public void prePersist() {
        this.dataEmprestimo = LocalDate.now(); //Define a data do emprestimo como a data atual
    }

}