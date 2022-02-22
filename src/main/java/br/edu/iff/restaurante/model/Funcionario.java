package br.edu.iff.restaurante.model;

import java.util.List;
import java.util.Objects;

public class Funcionario extends Pessoa{
    private String cargo;
    private String senha;
    private List<Comanda> comandas;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(cargo, that.cargo) && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, senha);
    }
}
