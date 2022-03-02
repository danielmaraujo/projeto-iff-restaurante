package br.edu.iff.restaurante.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn()
    private Comanda comanda;
    @Id
    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private int qtde;

    public Item() {
    }

    public Item(Comanda comanda, Produto produto, int qtde) {
        this.comanda = comanda;
        this.produto = produto;
        this.qtde = qtde;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(comanda, item.comanda) && Objects.equals(produto, item.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comanda, produto);
    }
}
