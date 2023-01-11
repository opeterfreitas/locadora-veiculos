package com.locap.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locap.locadora.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "vendedor")
    private List<Locacao> locacoes = new ArrayList<>();

    public Vendedor() {
        addPerfil(Perfil.VENDEDOR);
    }

    public Vendedor(Integer id, String nome, String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf, String cpf, String email, String senha) {
        super(id, nome, cep, logradouro, numero, complemento, bairro, localidade, uf, cpf, email, senha);
        addPerfil(Perfil.VENDEDOR);
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
