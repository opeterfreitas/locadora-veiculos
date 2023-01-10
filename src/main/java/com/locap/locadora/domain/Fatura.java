package com.locap.locadora.domain;

import jakarta.persistence.*;

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

    @OneToOne
    private Locacao locacao;

    public Fatura() {
    }

    public Fatura(Integer id, Double pagamentoBasico, Double taxa, Locacao locacao) {
        this.id = id;
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
        this.locacao = locacao;
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
