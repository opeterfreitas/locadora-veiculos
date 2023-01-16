package com.locap.locadora.services;

import com.locap.locadora.domain.*;
import com.locap.locadora.domain.dtos.LocacaoDTO;
import com.locap.locadora.domain.enums.Status;
import com.locap.locadora.repositories.LocacaoRepository;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VeiculoService veiculoService;

    public Locacao findById(Integer id) {
        Optional<Locacao> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Locacao> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Locacao create(LocacaoDTO objDTO) {
        return repository.save(newLocacao(objDTO));
    }

    @Transactional
    public Locacao update(Integer id, LocacaoDTO objDTO) {
        objDTO.setId(id);
        Locacao oldObj = findById(id);
        oldObj = newLocacao(objDTO);
        return repository.save(oldObj);
    }

    private Locacao newLocacao(LocacaoDTO objDTO) {

        Vendedor vendedor = vendedorService.findById(objDTO.getVendedor());
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Veiculo veiculo = veiculoService.findById(objDTO.getVeiculo());

        Locacao locacao = new Locacao();

        if (objDTO.getId() != null) {
            locacao.setId(objDTO.getId());
        }

        locacao.setVendedor(vendedor);
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setStatus(Status.toEnum(objDTO.getStatus()));
        locacao.setDataInicio(objDTO.getDataInicio());
        locacao.setDataFim(objDTO.getDataFim());

        if (objDTO.getStatus().equals(2)) {
            locacao.setDataDevolucao(LocalDateTime.now());
            processoFatura(locacao);
        }
        return locacao;
    }

    public void processoFatura(Locacao obj) {

        double minutos = Duration.between(obj.getDataInicio(), obj.getDataFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;

        if (horas <= 12.0) {
            pagamentoBasico = obj.getVeiculo().getPrecoPorHora() * Math.ceil(horas);
        } else {
            pagamentoBasico = obj.getVeiculo().getPrecoPorDia() * Math.ceil(horas / 24);
        }

        double taxa = taxa(pagamentoBasico);

        obj.setFatura(new Fatura(pagamentoBasico, taxa));
    }

    public double taxa(double pagamentoBasico) {
        if (pagamentoBasico <= 100.0) {
            return pagamentoBasico * 0.2;
        } else {
            return pagamentoBasico * 0.15;
        }
    }
}
