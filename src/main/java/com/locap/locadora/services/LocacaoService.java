package com.locap.locadora.services;

import com.locap.locadora.domain.Locacao;
import com.locap.locadora.repositories.LocacaoRepository;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;

    public Locacao findById(Integer id){
        Optional<Locacao> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Locacao> findAll() {
        return repository.findAll();
    }
}
