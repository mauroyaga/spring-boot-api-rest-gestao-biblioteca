package com.mauroyagadev.gestao_biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimo;

    public Integer getId() {
        return usuario_id;
    }
}
