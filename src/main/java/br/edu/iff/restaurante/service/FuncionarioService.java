package br.edu.iff.restaurante.service;

import br.edu.iff.restaurante.exception.NotFoundException;
import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.model.Permissao;
import br.edu.iff.restaurante.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@Service
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
            throw new NotFoundException("Funcionario não encontrado.");
        }
        return result.get();
    }

    public Funcionario save(Funcionario f){
        verificaCpf(f.getCpf());
        removePermissoesNulas(f);
        try {
            f.setSenha(new BCryptPasswordEncoder().encode(f.getSenha()));
            return repo.save(f);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
            throw new NotFoundException("Falha ao salvar o Funcionario.");
        }
    }
    private void verificaCpf(String cpf) {
        Optional<Funcionario> f = repo.findById(cpf);
        List<Cliente> c = repo.findClienteById(cpf);

        if (f.isPresent() || !c.isEmpty()) {
            throw new RuntimeException("CPF já cadastrado.");
        }
    }

    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        Funcionario obj = findById(f.getCpf());
        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        removePermissoesNulas(f);
        try {
            f.setCpf(obj.getCpf());
            f.setSenha(obj.getSenha());
            return repo.save(f);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() != null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException)
                    throw ((ConstraintViolationException) t);
            }
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

    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        if (!senhaAtual.isEmpty() && !novaSenha.isEmpty() && !confirmarNovaSenha.isEmpty()) {
            if (new BCryptPasswordEncoder().matches(senhaAtual, obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if (!novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
        }
    }

    public void removePermissoesNulas(Funcionario f){
        f.getPermissoes().removeIf( (Permissao p) -> {
            return p.getId()==null;
        });
        if(f.getPermissoes().isEmpty()){
            throw new RuntimeException("Funcionario deve conter no mínimo 1 permissão.");
        }
    }
}
