package br.edu.iff.restaurante.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa{
    @Column
    private String telefone;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone) {
        super(cpf, nome);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
