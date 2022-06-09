package br.edu.iff.restaurante.service;


import br.edu.iff.restaurante.exception.NotFoundException;
import br.edu.iff.restaurante.model.Produto;
import br.edu.iff.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
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
        if (!result.isPresent()) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return result.get();
    }

    public Produto save(Produto p){
        try {
            return repo.save(p);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
            throw new RuntimeException("Falha ao salvar o produto.");
        }
    }

    public Produto update(Produto p){
        Produto obj = findById(p.getId());
        try {
            p.setId(obj.getId());
            return repo.save(p);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
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
