package com.locap.locadora.services;

import com.locap.locadora.domain.Cliente;
import com.locap.locadora.domain.dtos.CepDTO;
import com.locap.locadora.domain.dtos.ClienteDTO;
import com.locap.locadora.repositories.ClienteRepository;
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

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);

        CepDTO cepDTO = consultaCep(objDTO.getCep());

        objDTO.setLogradouro(cepDTO.getLogradouro());
        objDTO.setBairro(cepDTO.getBairro());
        objDTO.setLocalidade(cepDTO.getLocalidade());
        objDTO.setUf(cepDTO.getUf());

        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    public CepDTO consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class)
                .getBody();
    }
}
