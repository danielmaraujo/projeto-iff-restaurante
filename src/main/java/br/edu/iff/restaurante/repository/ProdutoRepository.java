package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public List<Produto> findProdutosByNomeContaining(String pesquisa);
}