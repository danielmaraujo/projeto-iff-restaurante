package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    public List<Pessoa> findByCpf();
}