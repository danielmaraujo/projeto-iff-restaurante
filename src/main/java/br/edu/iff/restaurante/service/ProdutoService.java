package br.edu.iff.restaurante.service;


import br.edu.iff.restaurante.model.Produto;
import br.edu.iff.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ProdutoService {
    @Autowired
    ProdutoRepository repo;

    public List<Produto> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }

    public List<Produto> findAll(){
        return repo.findAll();
    }

    public Produto findById(int id){
        Optional<Produto> result = repo.findById(id);
        if (result.isPresent()) {
            throw new RuntimeException("Hotel não encontrado.");
        }
        return result.get();
    }

    public Produto save(Produto p){
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o produto.");
        }
    }

    public Produto update(Produto p){
        Produto obj = findById(p.getId());
        try {
            p.setId(obj.getId());
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o Produto.");
        }
    }

    public void delete(int id){
        Produto obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir o Produto.");
        }
    }


}
