package br.edu.iff.restaurante.security;

import br.edu.iff.restaurante.exception.NotFoundException;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.model.Permissao;
import br.edu.iff.restaurante.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioDetailsService implements UserDetailsService {
    @Autowired
    private FuncionarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<Funcionario> o = repo.findById(cpf);

        if (!o.isPresent()) {
            throw new UsernameNotFoundException("Funcionario não encontrado.");
        }

        Funcionario funcionario = o.get();

        return new User(funcionario.getCpf(), funcionario.getSenha(), getAuthorities(funcionario.getPermissoes()));
    }

    private List<GrantedAuthority> getAuthorities(List<Permissao> listaPermissoes){
        List<GrantedAuthority> listaGA = new ArrayList<>();

        for(Permissao p : listaPermissoes){
            listaGA.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
        }

        return listaGA;
    }
}
