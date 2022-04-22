package br.edu.iff.restaurante.service;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class FuncionarioService {
    @Autowired
    FuncionarioRepository repo;

    public List<Funcionario> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repo.findAll(p).toList();
    }
    public List<Funcionario> findAll(){
        return repo.findAll();
    }

    public Funcionario findById(String cpf) {
        Optional<Funcionario> result = repo.findById(cpf);
        if (!result.isPresent()) {
            throw new RuntimeException("Funcionario não encontrado.");
        }
        return result.get();
    }

    public Funcionario save(Funcionario f){
        verificaCpf(f.getCpf());
        try {
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Funcionario.");
        }
    }
    private void verificaCpf(String cpf) {
        Optional<Funcionario> f = repo.findById(cpf);
        List<Cliente> c = repo.findClienteById(cpf);

        if (f.isPresent() || !c.isEmpty()) {
            throw new RuntimeException("CPF já cadastrado.");
        }
    }

    public Funcionario update(Funcionario f){
        Funcionario obj = findById(f.getCpf());
        try {
            f.setCpf(obj.getCpf());
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao atualizar o Funcionario.");
        }
    }

    public void delete(String cpf){
        Funcionario obj = findById(cpf);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir o Funcionario.");
        }
    }
}
