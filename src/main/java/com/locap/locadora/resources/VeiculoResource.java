package com.locap.locadora.resources;

import com.locap.locadora.domain.Veiculo;
import com.locap.locadora.domain.dtos.VeiculoDTO;
import com.locap.locadora.services.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

    @Autowired
    private VeiculoService service;

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Integer id) {
        Veiculo obj = service.findById(id);
        return ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAll() {
        List<Veiculo> list = service.findAll();
        List<VeiculoDTO> listDTO = list.stream().map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> create(@Valid @RequestBody VeiculoDTO objDTO) {
        Veiculo newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Integer id, @Valid @RequestBody VeiculoDTO objDTO) {
        Veiculo obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeiculoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
