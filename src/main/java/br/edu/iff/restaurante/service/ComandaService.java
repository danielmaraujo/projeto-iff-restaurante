package br.edu.iff.restaurante.service;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Comanda;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.model.StatusComandaEnum;
import br.edu.iff.restaurante.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    @Autowired
    ComandaRepository repo;

    public List<Comanda> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }
    public List<Comanda> findAll(){
        return repo.findAll();
    }

    public Comanda findById(Integer id) {
        Optional<Comanda> result = repo.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException("Comanda não encontrada.");
        }
        return result.get();
    }

    public List<Comanda> findBetweenDates(LocalDateTime inicio, LocalDateTime termino) {
        List<Comanda> result = repo.findComandaBetweenDates(inicio, termino);
        if (result.isEmpty()) {
            throw new RuntimeException("Nenhuma comanda nesse período.");
        }
        return result;
    }

    public Comanda save(Comanda c){
        verificaComandaAberta(c.getNumMesa());
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar a comanda.");
        }
    }

    private void verificaComandaAberta(int numMesa) {
        List<Comanda> comandasAbertas = repo.findByStatus(StatusComandaEnum.aberta);

        comandasAbertas.forEach(comanda -> {
            if (comanda.getNumMesa().equals(numMesa)){
                throw new RuntimeException("Essa mesa já possui uma comanda aberta");
            }
        });
    }

    public Comanda update(Comanda c){
        Comanda obj = findById(c.getId());
        try {
            c.setId(obj.getId());
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar a comanda.");
        }
    }

    public void delete(Integer id){
        Comanda obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir a comanda.");
        }
    }

}
