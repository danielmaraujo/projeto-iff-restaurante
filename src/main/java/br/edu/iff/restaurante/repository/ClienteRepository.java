package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    public List<Pessoa> findByCpf(String cpf);
}