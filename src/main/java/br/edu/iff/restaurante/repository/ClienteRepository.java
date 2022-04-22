package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    @Query("SELECT f FROM Funcionario f WHERE f.cpf = :cpf")
    public List<Funcionario> findFuncionarioById(@Param("cpf") String cpf);
}