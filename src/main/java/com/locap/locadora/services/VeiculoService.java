package com.locap.locadora.services;

import com.locap.locadora.domain.Veiculo;
import com.locap.locadora.domain.dtos.VeiculoDTO;
import com.locap.locadora.repositories.PessoaRepository;
import com.locap.locadora.repositories.VeiculoRepository;
import com.locap.locadora.services.exceptions.DataIntegrityViolationException;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Veiculo findById(Integer id) {
        Optional<Veiculo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    public Veiculo create(VeiculoDTO objDTO) {
        objDTO.setId(null);
        Veiculo newObj = new Veiculo(objDTO);
        return repository.save(newObj);
    }

    public Veiculo update(Integer id, VeiculoDTO objDTO) {
        objDTO.setId(id);
        Veiculo oldObj = findById(id);
        oldObj = new Veiculo(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Veiculo obj = findById(id);
        if (obj.getLocacoes().size() > 0) {
            throw new DataIntegrityViolationException("Veiculo possui locações e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}