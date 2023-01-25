package com.locap.locadora.domain;

import com.locap.locadora.domain.dtos.FaturaDTO;

import javax.persistence.*;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Locacao locacao;

    public Fatura() {
    }

    public Fatura(FaturaDTO objDTO) {
        this.id = objDTO.getId();
        this.pagamentoBasico = objDTO.getPagamentoBasico();
        this.taxa = objDTO.getTaxa();
    }

    public Fatura(Integer id, Double pagamentoBasico, Double taxa, Locacao locacao) {
        this.id = id;
        this.pagamentoBasico = pagamentoBasico;
        this.taxa = taxa;
        this.locacao = locacao;
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

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
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
