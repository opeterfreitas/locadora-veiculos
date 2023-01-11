package com.locap.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locap.locadora.domain.dtos.ClienteDTO;
import com.locap.locadora.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes = new ArrayList<>();

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf, String cpf, String email, String senha) {
        super(id, nome, cep, logradouro, numero, complemento, bairro, localidade, uf, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO objDTO) {
        this.id = objDTO.getId();
        this.nome = objDTO.getNome();
        this.cep = objDTO.getCep();
        this.logradouro = objDTO.getLogradouro();
        this.numero = objDTO.getNumero();
        this.complemento = objDTO.getComplemento();
        this.bairro = objDTO.getBairro();
        this.localidade = objDTO.getLocalidade();
        this.uf = objDTO.getUf();
        this.cpf = objDTO.getCpf();
        this.email = objDTO.getEmail();
        this.senha = objDTO.getSenha();
        this.perfis = objDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = objDTO.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
