package com.locap.locadora.repositories;

import com.locap.locadora.domain.Locacao;
import com.locap.locadora.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

    @Query("SELECT obj FROM Locacao obj WHERE obj.veiculo = :veiculo " +
            "AND (obj.dataInicio <= :dataInicio AND obj.dataFim >= :dataInicio)" +
            "OR (obj.dataInicio <= :dataFim AND obj.dataFim >= :dataFim)" +
            "OR (obj.dataInicio >= :dataInicio AND obj.dataFim <= :dataInicio)")
    Optional<Locacao> existeLocacao(@Param("veiculo") Veiculo veiculo, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    List<Locacao> findBydataDevolucaoIsNull();

    List<Locacao> findBydataDevolucaoNotNull();

}