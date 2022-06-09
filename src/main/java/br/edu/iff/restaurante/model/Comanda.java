package br.edu.iff.restaurante.model;

import br.edu.iff.restaurante.annotation.HorarioFechamentoValidation;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
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
    @NotNull(message = "Horário de abertura obrigatório.")
    @PastOrPresent(message = "Horário de abertura não pode ser futuro.")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime horarioAbertura;

    @Positive(message = "Apenas valores positivos.")
    @Digits(integer = 7, fraction = 2, message = "Formato inválido")
    private BigDecimal valorTotal;

    @Column(nullable = false)
    @NotNull(message = "Número da mesa obrigatório.")
    @Digits(integer = 2, fraction = 0, message = "Formato inválido")
    @Min(value = 1, message = "Mesa não existe")
    @Max(value = 21, message = "Mesa não existe")
    private Integer numMesa;

    @PastOrPresent(message = "Horário de fechamento não pode ser futuro.")
    //@HorarioFechamentoValidation
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime horarioFechamento;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Forma de pagamento obrigatória.")
    private FormaPagamentoEnum formaPagamento;

    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Status da comanda obrigatório.")
    private StatusComandaEnum status;

    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull
    @Valid
    private List<Item> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Funcionário obrigatório.")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Cliente obrigatório.")
    private Cliente cliente;

    public Comanda() {
        this.horarioAbertura = LocalDateTime.now();
        this.status = StatusComandaEnum.aberta;
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

    public void atualizaValorTotal(){
        itens.forEach(item -> {
            this.valorTotal = BigDecimal.ZERO;
            this.valorTotal = valorTotal.add(item.getProduto().getValor());
        });
    }
}
