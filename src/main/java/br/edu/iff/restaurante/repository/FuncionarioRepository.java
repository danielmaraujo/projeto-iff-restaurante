package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Cliente;
import br.edu.iff.restaurante.model.Funcionario;
import br.edu.iff.restaurante.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    public List<Cliente> findClienteById(@Param("cpf") String cpf);
}