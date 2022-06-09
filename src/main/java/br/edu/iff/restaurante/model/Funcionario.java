package br.edu.iff.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario extends Pessoa{

    @Column(nullable = false, length = 20)
    @NotBlank(message = "Cargo obrigatório.")
    @Length(max = 20, message = "Máximo de caracteres: 20")
    private String cargo;

    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 6, message = "Minimo de caracteres: 6")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Comanda> comandas = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @OrderColumn
    @Size(min = 1, message = "Funcionário precisa ter no mínimo 1 permissão.")
    private List<Permissao> permissoes = new ArrayList<>();

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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {
        return this.getNome() + " - " + this.getCargo();
    }
}
