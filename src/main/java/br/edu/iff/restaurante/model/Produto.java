package br.edu.iff.restaurante.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true, length = 30)
    @NotBlank(message = "Campo obrigatório.")
    @Length(max = 30, message = "Máximo de caracteres: 30")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "Campo obrigatório.")
    @Positive(message = "Apenas valores positivos.")
    @Digits(integer = 7, fraction = 2, message = "Formato inválido")
    private BigDecimal valor;

    @Column(length = 50)
    private String descricao;

    public Produto() {
    }

    public Produto(String nome, BigDecimal valor) {
        this.valor = valor;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}