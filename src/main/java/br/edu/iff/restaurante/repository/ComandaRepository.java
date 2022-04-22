package br.edu.iff.restaurante.repository;

import br.edu.iff.restaurante.model.Comanda;
import br.edu.iff.restaurante.model.StatusComandaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer>{
    public List<Comanda> findByStatus(StatusComandaEnum status);
    @Query("SELECT c FROM Comanda c WHERE (c.horarioAbertura BETWEEN :inicio AND :termino)")
    public List<Comanda> findComandaBetweenDates(LocalDateTime inicio, LocalDateTime termino);

}