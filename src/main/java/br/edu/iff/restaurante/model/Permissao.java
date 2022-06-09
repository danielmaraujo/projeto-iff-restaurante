package br.edu.iff.restaurante.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Permissao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Permissao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
