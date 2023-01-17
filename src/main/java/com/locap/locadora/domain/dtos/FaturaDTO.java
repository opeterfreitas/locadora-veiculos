package com.locap.locadora.domain.dtos;

import com.locap.locadora.domain.Fatura;

import java.io.Serializable;

public class FaturaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Double pagamentoBasico;
    private Double taxa;

    public FaturaDTO() {
    }

    public FaturaDTO(Fatura obj) {
        this.id = obj.getId();
        this.pagamentoBasico = obj.getPagamentoBasico();
        this.taxa = obj.getTaxa();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPagamentoBasico() {
        return pagamentoBasico;
    }

    public void setPagamentoBasico(Double pagamentoBasico) {
        this.pagamentoBasico = pagamentoBasico;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

}
