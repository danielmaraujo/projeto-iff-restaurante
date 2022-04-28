package br.edu.iff.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
    @JsonIgnoreProperties(allowGetters = false, allowSetters = true)
    private String senha;

    @JsonIgnore
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
