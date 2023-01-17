package com.locap.locadora.domain.dtos;

import com.locap.locadora.domain.Veiculo;
import com.locap.locadora.domain.enums.Categoria;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

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

    public VeiculoDTO() {
    }

    public VeiculoDTO(Veiculo obj) {
        this.id = obj.getId();
        this.modelo = obj.getModelo();
        this.categoria = obj.getCategoria();
        this.precoPorDia = obj.getPrecoPorDia();
        this.precoPorHora = obj.getPrecoPorHora();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecoPorDia() {
        return precoPorDia;
    }

    public void setPrecoPorDia(Double precoPorDia) {
        this.precoPorDia = precoPorDia;
    }

    public Double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora(Double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }
}
