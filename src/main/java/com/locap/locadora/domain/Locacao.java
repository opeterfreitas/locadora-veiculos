package com.locap.locadora.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locap.locadora.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacaoReserva = LocalDateTime.now();
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFim;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDevolucao;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    @OneToOne(cascade = CascadeType.ALL)
    private Fatura fatura;

    public Locacao(Integer id, LocalDateTime dataInicio, LocalDateTime dataFim, Status status, Cliente cliente, Vendedor vendedor, Veiculo veiculo) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.veiculo = veiculo;
    }
}
