package br.edu.iff.restaurante.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario extends Pessoa{

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String senha;

    @JsonBackReference
    @OneToMany(mappedBy = "funcionario")
    private List<Comanda> comandas = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, String cargo, String senha) {
        super(cpf, nome);
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
