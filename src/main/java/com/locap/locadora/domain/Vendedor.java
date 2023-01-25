package com.locap.locadora.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locap.locadora.domain.dtos.VendedorDTO;
import com.locap.locadora.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "vendedor")
    private List<Locacao> locacoes = new ArrayList<>();

    public Vendedor(Integer id, String nome, String cep, String logradouro, String numero, String complemento, String bairro, String localidade, String uf, String cpf, String email, String senha) {
        super(id, nome, cep, logradouro, numero, complemento, bairro, localidade, uf, cpf, email, senha);
        addPerfil(Perfil.VENDEDOR);
    }

    public Vendedor(VendedorDTO objDTO) {
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
        addPerfil(Perfil.VENDEDOR);
    }
}
