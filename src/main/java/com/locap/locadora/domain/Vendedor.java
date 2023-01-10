package com.locap.locadora.domain;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    private static final long serialVersionUID = 1L;

    private List<Locacao> locacoes = new ArrayList<>();

    public Vendedor() {
    }

    public Vendedor(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
