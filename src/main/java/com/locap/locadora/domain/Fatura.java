package com.locap.locadora.domain;

import com.locap.locadora.domain.dtos.FaturaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double pagamentoBasico;
    private Double taxa;
    @OneToOne(cascade = CascadeType.ALL)
    private Locacao locacao;

    public Fatura(FaturaDTO objDTO) {
        this.id = objDTO.getId();
        this.pagamentoBasico = objDTO.getPagamentoBasico();
        this.taxa = objDTO.getTaxa();
    }

    public Fatura(Integer id, Double pagamentoBasico, Double taxa) {
        this.id = id;
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
    }

    public Fatura(Double pagamentoBasico, Double taxa, Locacao locacao) {
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
        this.locacao = locacao;
    }

    public Double getPagamentoTotal() {
        return getPagamentoBasico() + getTaxa();
    }

}
