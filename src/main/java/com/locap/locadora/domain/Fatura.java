package com.locap.locadora.domain;

import com.locap.locadora.domain.dtos.FaturaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Fatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double pagamentoBasico;
    private Double taxa;

    public Fatura() {
    }

    public Fatura(FaturaDTO obj) {
        this.id = obj.getId();
        this.pagamentoBasico = obj.getPagamentoBasico();
        this.taxa = obj.getTaxa();
    }

    public Fatura(Integer id, Double pagamentoBasico, Double taxa) {
        this.id = id;
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
    }

    public Fatura(Double pagamentoBasico, Double taxa) {
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
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

    public Double getPagamentoTotal() {
        return getPagamentoBasico() + getTaxa();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return id.equals(fatura.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
