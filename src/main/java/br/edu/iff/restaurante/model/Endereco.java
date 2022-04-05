package br.edu.iff.restaurante.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Embeddable
public class Endereco {
    @Column(nullable = false, length = 50)
    @NotBlank(message = "Rua obrigatória.")
    @Length(max = 50, message = "Máximo de caracteres: 50")
    private String rua;
    @Column(nullable = false)
    @Digits(integer = 4, fraction = 0, message = "Formato de número inválido.")
    private Integer num;
    @Column(nullable = false, length = 30)
    @NotBlank(message = "Bairro obrigatório.")
    @Length(max = 30, message = "Máximo de caracteres: 30")
    private String bairro;
    @Column(nullable = false, length = 30)
    @NotBlank(message = "Cidade obrigatória.")
    @Length(max = 30, message = "Máximo de caracteres: 30")
    private String cidade;
    @Column(nullable = false, length = 8)
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Formato de CEP deve ser \'XXXXX-XXX\'")
    private String cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}