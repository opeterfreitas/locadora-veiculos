package com.locap.locadora.domain.dtos;

import com.locap.locadora.domain.Veiculo;
import com.locap.locadora.domain.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "O campo MODELO é requerido")
    private String modelo;
    @NotNull(message = "O campo CATEGORIA é requerido")
    private Categoria categoria;
    @NotNull(message = "O campo PREÇO POR DIA é requerido")
    private Double precoPorDia;
    @NotNull(message = "O campo PREÇO POR HORA é requerido")
    private Double precoPorHora;

    public VeiculoDTO(Veiculo obj) {
        this.id = obj.getId();
        this.modelo = obj.getModelo();
        this.categoria = obj.getCategoria();
        this.precoPorDia = obj.getPrecoPorDia();
        this.precoPorHora = obj.getPrecoPorHora();
    }
}
