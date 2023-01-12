package com.locap.locadora.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locap.locadora.domain.Vendedor;
import com.locap.locadora.domain.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class VendedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "O campo NOME é requerido")
    private String nome;
    @NotNull(message = "O campo CEP é requerido")
    private String cep;
    private String logradouro;
    @NotNull(message = "O campo NUMERO é requerido")
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    @CPF
    @NotNull(message = "O campo CPF é requerido")
    private String cpf;
    @Email
    @NotNull(message = "O campo EMAIL é requerido")
    private String email;
    @NotNull(message = "O campo SENHA é requerido")
    private String senha;
    private Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public VendedorDTO() {
        addPerfil(Perfil.VENDEDOR);
    }

    public VendedorDTO(Vendedor obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cep = obj.getCep();
        this.logradouro = obj.getLogradouro();
        this.numero = obj.getNumero();
        this.complemento = obj.getComplemento();
        this.bairro = obj.getBairro();
        this.localidade = obj.getLocalidade();
        this.uf = obj.getUf();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.VENDEDOR);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
