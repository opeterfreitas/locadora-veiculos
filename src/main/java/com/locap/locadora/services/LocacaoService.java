package com.locap.locadora.services;

import com.locap.locadora.domain.*;
import com.locap.locadora.domain.dtos.LocacaoDTO;
import com.locap.locadora.domain.enums.Status;
import com.locap.locadora.repositories.LocacaoRepository;
import com.locap.locadora.services.exceptions.DataIntegrityViolationException;
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

    public List<Locacao> findAllOpen() {
        List<Locacao> list = repository.findBydataDevolucaoIsNull();
        return list;
    }

    public List<Locacao> findAllClose() {
        List<Locacao> list = repository.findBydataDevolucaoNotNull();
        return list;
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

    @Transactional
    public void delete(Integer id) {
        Locacao obj = findById(id);
        repository.deleteById(id);
    }

    private Locacao newLocacao(LocacaoDTO objDTO) {

        Vendedor vendedor = vendedorService.findById(objDTO.getVendedor());
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Veiculo veiculo = veiculoService.findById(objDTO.getVeiculo());

        Locacao locacao = new Locacao();

        if (objDTO.getId() != null) {
            locacao.setId(objDTO.getId());
        }

        if (objDTO.getStatus().equals(2)) {
            locacao.setDataDevolucao(LocalDateTime.now());
            processoFatura(objDTO);
        }

        locacao.setVendedor(vendedor);
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setStatus(Status.toEnum(objDTO.getStatus()));
        locacao.setDataInicio(objDTO.getDataInicio());
        locacao.setDataFim(objDTO.getDataFim());

        Optional<Locacao> existeLocacao = repository.existeLocacao(locacao.getVeiculo(), locacao.getDataInicio(), locacao.getDataFim());
        if (existeLocacao.isPresent() && objDTO.getId() == null) {
            throw new DataIntegrityViolationException("Data de inicio dentro de período já locado");
        }

        return locacao;
    }

    public void processoFatura(LocacaoDTO objDTO) {

        Locacao locacao = findById(objDTO.getId());

        double minutos = Duration.between(locacao.getDataInicio(), locacao.getDataFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;

        if (horas <= 12.0) {
            pagamentoBasico = locacao.getVeiculo().getPrecoPorHora() * Math.ceil(horas);
        } else {
            pagamentoBasico = locacao.getVeiculo().getPrecoPorDia() * Math.ceil(horas / 24);
        }

        double taxa = taxa(pagamentoBasico);

        locacao.setFatura(new Fatura(pagamentoBasico, taxa, locacao));
    }

    public double taxa(double pagamentoBasico) {
        if (pagamentoBasico <= 100.0) {
            return pagamentoBasico * 0.2;
        } else {
            return pagamentoBasico * 0.15;
        }
    }
}
