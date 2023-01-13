package com.locap.locadora.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locap.locadora.domain.Locacao;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LocacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacaoReserva = LocalDateTime.now();
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFim;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDevolucao;
    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;
    private String nomeCliente;
    @NotNull(message = "O campo VENDEDOR é requerido")
    private Integer vendedor;
    private String nomeVendedor;
    @NotNull(message = "O campo VEICULO é requerido")
    private Integer veiculo;

    public LocacaoDTO() {
    }

    public LocacaoDTO(Locacao obj) {
        this.id = obj.getId();
        this.dataCriacaoReserva = obj.getDataCriacaoReserva();
        this.dataInicio = obj.getDataInicio();
        this.dataFim = obj.getDataFim();
        this.dataDevolucao = obj.getDataDevolucao();
        this.status = obj.getStatus().getCodigo();
        this.cliente = obj.getCliente().getId();
        this.nomeCliente = obj.getCliente().getNome();
        this.vendedor = obj.getVendedor().getId();
        this.nomeVendedor = obj.getVendedor().getNome();
        this.veiculo = obj.getVeiculo().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacaoReserva() {
        return dataCriacaoReserva;
    }

    public void setDataCriacaoReserva(LocalDateTime dataCriacaoReserva) {
        this.dataCriacaoReserva = dataCriacaoReserva;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getVendedor() {
        return vendedor;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedor = vendedor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public Integer getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Integer veiculo) {
        this.veiculo = veiculo;
    }
}
