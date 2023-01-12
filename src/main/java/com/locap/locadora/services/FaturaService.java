package com.locap.locadora.services;

import com.locap.locadora.domain.Fatura;
import com.locap.locadora.domain.dtos.FaturaDTO;
import com.locap.locadora.repositories.FaturaRepository;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository repository;

    public Fatura findById(Integer id) {
        Optional<Fatura> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Fatura> findAll() {
        return repository.findAll();
    }

    public Fatura create(FaturaDTO objDTO) {
        objDTO.setId(null);
        Fatura newObj = new Fatura(objDTO);
        return repository.save(newObj);
    }

    public Fatura update(Integer id, FaturaDTO objDTO) {
        objDTO.setId(id);
        Fatura oldObj = findById(id);
        oldObj = new Fatura(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Fatura obj = findById(id);
        repository.deleteById(id);
    }
}
