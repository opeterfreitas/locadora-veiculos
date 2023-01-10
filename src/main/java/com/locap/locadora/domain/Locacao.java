package com.locap.locadora.domain;

import com.locap.locadora.domain.enums.Categoria;
import com.locap.locadora.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataReserva = LocalDateTime.now();
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataDevolucao;

    private Categoria categoria;
    private Status status;

    private Cliente cliente;
    private Vendedor vendedor;
    private Veiculo veiculo;
    private Fatura fatura;

    public Locacao() {
    }

    public Locacao(Integer id, LocalDateTime dataInicio, LocalDateTime dataFim, Categoria categoria, Status status, Cliente cliente, Vendedor vendedor, Veiculo veiculo, Fatura fatura) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.categoria = categoria;
        this.status = status;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.veiculo = veiculo;
        this.fatura = fatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacao locacao = (Locacao) o;
        return id.equals(locacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
