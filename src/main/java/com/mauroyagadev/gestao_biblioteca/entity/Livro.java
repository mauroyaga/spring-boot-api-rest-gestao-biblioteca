package com.mauroyagadev.gestao_biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe que representa a entidade Livro
 * Ela contém os atributos que representam as colunas da tabela livro no banco de dados
 * Além disso, possui o relacionamento com a entidade Emprestimo
 * */
@Data
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private int livro_id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String autor;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @Column(nullable = false, length = 50)
    private String categoria;

    @JsonManagedReference(value = "livro-emprestimo") //Evita recursão infinita da serialização
    @OneToMany(mappedBy = "livro") //Define o relacionamento um para muitos com a entidade Emprestimo
    private List<Emprestimo> emprestimo; //Lista de emprestimos relacionados ao livro
}
