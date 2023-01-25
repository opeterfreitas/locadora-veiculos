package com.locap.locadora.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locap.locadora.domain.Locacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
