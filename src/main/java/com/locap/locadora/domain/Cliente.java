package com.locap.locadora.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;

    private List<Locacao> locacoes = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
