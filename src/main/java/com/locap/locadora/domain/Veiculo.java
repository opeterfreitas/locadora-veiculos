package com.locap.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locap.locadora.domain.dtos.VeiculoDTO;
import com.locap.locadora.domain.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelo;
    private Categoria categoria;
    private Double precoPorDia;
    private Double precoPorHora;
    @JsonIgnore
    @OneToMany(mappedBy = "veiculo")
    private List<Locacao> locacoes = new ArrayList<>();

    public Veiculo(Integer id, String modelo, Categoria categoria, Double precoPorDia, Double precoPorHora) {
        this.id = id;
        this.modelo = modelo;
        this.categoria = categoria;
        this.precoPorDia = precoPorDia;
        this.precoPorHora = precoPorHora;
    }

    public Veiculo(VeiculoDTO obj) {
        this.id = obj.getId();
        this.modelo = obj.getModelo();
        this.categoria = obj.getCategoria();
        this.precoPorDia = obj.getPrecoPorDia();
        this.precoPorHora = obj.getPrecoPorHora();
    }
}
