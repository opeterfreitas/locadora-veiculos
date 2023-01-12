package com.locap.locadora.services;

import com.locap.locadora.domain.*;
import com.locap.locadora.domain.enums.Categoria;
import com.locap.locadora.domain.enums.Perfil;
import com.locap.locadora.domain.enums.Status;
import com.locap.locadora.repositories.FaturaRepository;
import com.locap.locadora.repositories.LocacaoRepository;
import com.locap.locadora.repositories.PessoaRepository;
import com.locap.locadora.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    public void instanciaDB() {

        Vendedor v1 = new Vendedor(null, "Peter Freitas","38414042","","","","","","", "550.482.150-95", "peter@mail.com", "123");
        v1.addPerfil(Perfil.ADMIN);
        Vendedor v2 = new Vendedor(null, "Richard Stallman","38414042","","","","","","","903.347.070-56", "stallman@mail.com", "123");
        Vendedor v3 = new Vendedor(null, "Claude Elwood Shannon", "38414042","","","","","","","271.068.470-54", "shannon@mail.com", "123");
        Vendedor v4 = new Vendedor(null, "Tim Berners-Lee", "38414042","","","","","","","162.720.120-39", "lee@mail.com", "123");
        Vendedor v5 = new Vendedor(null, "Linus Torvalds", "38414042","","","","","","","778.556.170-27", "linus@mail.com", "123");

        Cliente c1 = new Cliente(null, "Albert Einstein", "38414042","","","","","","","111.661.890-74", "einstein@mail.com", "123");
        Cliente c2 = new Cliente(null, "Marie Curie", "38414042","","","","","","","322.429.140-06", "curie@mail.com", "123");
        Cliente c3 = new Cliente(null, "Charles Darwin","38414042","","","","","","","792.043.830-62", "darwin@mail.com", "123");
        Cliente c4 = new Cliente(null, "Stephen Hawking", "38414042","","","","","","","177.409.680-30", "hawking@mail.com", "123");
        Cliente c5 = new Cliente(null, "Max Planck", "38414042","","","","","","","081.399.300-83", "planck@mail.com", "123");

        Veiculo vc1 = new Veiculo(null, "Uno Fire 1.0", Categoria.HATCH, 110.0, 11.0);
        Veiculo vc2 = new Veiculo(null, "Gol G6 1.0", Categoria.HATCH, 120.0, 12.0);
        Veiculo vc3 = new Veiculo(null, "Onix 1.0", Categoria.HATCH, 130.0, 13.0);
        Veiculo vc4 = new Veiculo(null, "Voyage 1.0", Categoria.SEDAN, 150.0, 15.0);
        Veiculo vc5 = new Veiculo(null, "Classic 1.0", Categoria.SEDAN, 160.0, 16.0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Locacao l1 = new Locacao(null, LocalDateTime.parse("10/01/2023 08:00", formatter), LocalDateTime.parse("12/01/2023 12:00", formatter), Status.ANDAMENTO, c1, v1, vc1);
        Locacao l2 = new Locacao(null, LocalDateTime.parse("11/01/2023 08:00", formatter), LocalDateTime.parse("13/01/2023 12:00", formatter), Status.ANDAMENTO, c2, v1, vc2);
        Locacao l3 = new Locacao(null, LocalDateTime.parse("11/01/2023 08:00", formatter), LocalDateTime.parse("15/01/2023 12:00", formatter), Status.ANDAMENTO, c3, v2, vc3);
        Locacao l4 = new Locacao(null, LocalDateTime.parse("07/01/2023 08:00", formatter), LocalDateTime.parse("09/01/2023 12:00", formatter), Status.ATRASADO, c4, v4, vc4);
        Locacao l5 = new Locacao(null, LocalDateTime.parse("02/01/2023 08:00", formatter), LocalDateTime.parse("07/01/2023 12:00", formatter), Status.DEVOLVIDO, c5, v5, vc5);

        //Fatura f1 = new Fatura(null, 450.0, 20.0);

        pessoaRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, c1, c2, c3, c4, c5));
        veiculoRepository.saveAll(Arrays.asList(vc1, vc2, vc3, vc4, vc5));
        locacaoRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
        //faturaRepository.saveAll(Arrays.asList(f1));
    }
}