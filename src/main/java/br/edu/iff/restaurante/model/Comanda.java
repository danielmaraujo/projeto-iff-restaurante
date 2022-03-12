package br.edu.iff.restaurante.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime horarioAbertura;

    private BigDecimal valorTotal;

    @Column(nullable = false)
    private Integer numMesa;

    private LocalDateTime horarioFechamento;

    @Enumerated(EnumType.STRING)
    private FormaPagamentoEnum formaPagamento;

    @Enumerated(EnumType.ORDINAL)
    private StatusComandaEnum status;

    @ElementCollection
    private List<Item> itens = new ArrayList<>();

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Funcionario funcionario;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    public Comanda() {
    }

    public Comanda(Integer numMesa, Funcionario funcionario) {
        this.numMesa = numMesa;
        this.funcionario = funcionario;
        this.horarioAbertura = LocalDateTime.now();
        this.status = StatusComandaEnum.aberta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(LocalDateTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Integer numMesa) {
        this.numMesa = numMesa;
    }

    public LocalDateTime getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(LocalDateTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusComandaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusComandaEnum status) {
        this.status = status;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(id, comanda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
