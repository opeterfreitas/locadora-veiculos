package com.locap.locadora.services;

import com.locap.locadora.domain.Pessoa;
import com.locap.locadora.domain.Vendedor;
import com.locap.locadora.domain.dtos.CepDTO;
import com.locap.locadora.domain.dtos.VendedorDTO;
import com.locap.locadora.repositories.PessoaRepository;
import com.locap.locadora.repositories.VendedorRepository;
import com.locap.locadora.services.exceptions.DataIntegrityViolationException;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Vendedor findById(Integer id) {
        Optional<Vendedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Vendedor> findAll() {
        return repository.findAll();
    }

    public Vendedor create(VendedorDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        validaCep(objDTO);
        Vendedor newObj = new Vendedor(objDTO);
        return repository.save(newObj);
    }

    public Vendedor update(Integer id, VendedorDTO objDTO) {
        objDTO.setId(id);
        Vendedor oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        validaCep(objDTO);
        oldObj = new Vendedor(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Vendedor obj = findById(id);
        if (obj.getLocacoes().size() > 0) {
            throw new DataIntegrityViolationException("Vendedor possui locações e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    public CepDTO consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class)
                .getBody();
    }

    public void validaCep(VendedorDTO objDTO) {
        CepDTO cepDTO = consultaCep(objDTO.getCep());
        objDTO.setLogradouro(cepDTO.getLogradouro());
        objDTO.setBairro(cepDTO.getBairro());
        objDTO.setLocalidade(cepDTO.getLocalidade());
        objDTO.setUf(cepDTO.getUf());
    }

    private void validaPorCpfEEmail(VendedorDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
