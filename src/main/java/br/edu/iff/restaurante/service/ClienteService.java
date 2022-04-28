package br.edu.iff.restaurante.service;

import br.edu.iff.restaurante.exception.NotFoundException;
import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public List<Cliente> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }
    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public Cliente findById(String cpf) {
        Optional<Cliente> result = repo.findById(cpf);
        if (!result.isPresent()) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        return result.get();
    }

    public Cliente save(Cliente c){
        verificaCpf(c.getCpf());
        try {
            return repo.save(c);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
            throw new RuntimeException("Falha ao salvar o cliente.");
        }
    }
    private void verificaCpf(String cpf) {
        Optional<Cliente> c = repo.findById(cpf);
        List<Funcionario> f = repo.findFuncionarioById(cpf);

        if (c.isPresent() || !f.isEmpty()) {
            throw new RuntimeException("CPF já cadastrado.");
        }
    }

    public Cliente update(Cliente c){
        Cliente obj = findById(c.getCpf());
        try {
            c.setCpf(obj.getCpf());
            return repo.save(c);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
            throw new RuntimeException("Falha ao atualizar o cliente.");
        }
    }

    public void delete(String cpf){
        Cliente obj = findById(cpf);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir o cliente.");
        }
    }


}
