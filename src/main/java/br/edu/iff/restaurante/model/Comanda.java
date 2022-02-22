package br.edu.iff.restaurante.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDateTime horarioAbertura;
    private BigDecimal valorTotal;
    private Integer numMesa;
    private LocalDateTime horarioFechamento;
    private FormaPagamentoEnum formaPagamento;
    private StatusComandaEnum status;
    private List<Item> itens;
    private Funcionario funcionario;

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
