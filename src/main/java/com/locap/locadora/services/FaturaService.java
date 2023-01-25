package com.locap.locadora.services;

import com.locap.locadora.domain.Fatura;
import com.locap.locadora.domain.dtos.FaturaDTO;
import com.locap.locadora.repositories.FaturaRepository;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FaturaService {

    private final FaturaRepository repository;

    public Fatura findById(Integer id) {
        Optional<Fatura> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Fatura> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Fatura create(FaturaDTO objDTO) {
        objDTO.setId(null);
        Fatura newObj = new Fatura(objDTO);
        return repository.save(newObj);
    }

    @Transactional
    public Fatura update(Integer id, FaturaDTO objDTO) {
        objDTO.setId(id);
        Fatura oldObj = findById(id);
        oldObj = new Fatura(objDTO);
        return repository.save(oldObj);
    }

    @Transactional
    public void delete(Integer id) {
        Fatura obj = findById(id);
        repository.deleteById(id);
    }
}
