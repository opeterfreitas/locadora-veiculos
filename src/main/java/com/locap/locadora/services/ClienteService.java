package com.locap.locadora.services;

import com.locap.locadora.domain.Cliente;
import com.locap.locadora.domain.Pessoa;
import com.locap.locadora.domain.dtos.CepDTO;
import com.locap.locadora.domain.dtos.ClienteDTO;
import com.locap.locadora.repositories.ClienteRepository;
import com.locap.locadora.repositories.PessoaRepository;
import com.locap.locadora.services.exceptions.DataIntegrityViolationException;
import com.locap.locadora.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);

        CepDTO cepDTO = consultaCep(objDTO.getCep());

        objDTO.setLogradouro(cepDTO.getLogradouro());
        objDTO.setBairro(cepDTO.getBairro());
        objDTO.setLocalidade(cepDTO.getLocalidade());
        objDTO.setUf(cepDTO.getUf());

        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public CepDTO consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class)
                .getBody();
    }
}
