package com.locap.locadora.services;

import com.locap.locadora.domain.Cliente;
import com.locap.locadora.domain.Locacao;
import com.locap.locadora.domain.Veiculo;
import com.locap.locadora.domain.Vendedor;
import com.locap.locadora.domain.dtos.LocacaoDTO;
import com.locap.locadora.domain.enums.Status;
import com.locap.locadora.repositories.LocacaoRepository;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Locacao create(LocacaoDTO objDTO) {
        return repository.save(newLocacao(objDTO));
    }

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

        if (objDTO.getStatus().equals(2)){
            locacao.setDataDevolucao(LocalDateTime.now());
        }

        locacao.setVendedor(vendedor);
        locacao.setCliente(cliente);
        locacao.setVeiculo(veiculo);
        locacao.setStatus(Status.toEnum(objDTO.getStatus()));
        locacao.setDataInicio(objDTO.getDataInicio());
        locacao.setDataFim(objDTO.getDataFim());

        return locacao;
    }
}
