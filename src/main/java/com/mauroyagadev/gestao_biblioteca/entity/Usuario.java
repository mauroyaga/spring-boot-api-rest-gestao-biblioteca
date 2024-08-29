package com.mauroyagadev.gestao_biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Classe que representa a entidade Usuario
 * Ela contém os atributos que representam as colunas da tabela usuario no banco de dados
 * Além disso, possui o relacionamento com a entidade Emprestimo
 * */
@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private  int usuario_id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length =  20)
    private String telefone;


    @JsonManagedReference(value = "usuario-emprestimo") //Evita recursão infinita da serialização
    @OneToMany(mappedBy = "usuario") //Define o relacionamento um para muitos com a entidade Emprestimo
    private List<Emprestimo> emprestimo;  //Lista de emprestimos relacionados ao usuario

}
