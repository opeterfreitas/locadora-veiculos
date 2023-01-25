package com.locap.locadora.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locap.locadora.domain.Cliente;
import com.locap.locadora.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {
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

    public ClienteDTO(Cliente obj) {
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
        addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

}
