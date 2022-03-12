package br.edu.iff.restaurante.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne()
    @JoinColumn(nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private int qtde;

    public Item() {
    }

    public Item(Produto produto, int qtde) {
        this.produto = produto;
        this.qtde = qtde;
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
        return qtde == item.qtde && Objects.equals(produto, item.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, qtde);
    }
}
