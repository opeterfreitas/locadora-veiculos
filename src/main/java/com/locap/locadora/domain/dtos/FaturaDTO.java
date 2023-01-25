package com.locap.locadora.domain.dtos;

import com.locap.locadora.domain.Fatura;
import com.locap.locadora.domain.Locacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Double pagamentoBasico;
    private Double taxa;
    private Locacao locacao;

    public FaturaDTO(Fatura obj) {
        this.id = obj.getId();
        this.pagamentoBasico = obj.getPagamentoBasico();
        this.taxa = obj.getTaxa();
        this.locacao = obj.getLocacao();
    }
}
