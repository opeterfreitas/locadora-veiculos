package com.locap.locadora.resources;

import com.locap.locadora.domain.Vendedor;
import com.locap.locadora.domain.dtos.VendedorDTO;
import com.locap.locadora.services.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vendedores")
public class VendedorResource {

    private final VendedorService service;

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Integer id) {
        Vendedor obj = service.findById(id);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> findAll() {
        List<Vendedor> list = service.findAll();
        List<VendedorDTO> listDTO = list.stream().map(obj -> new VendedorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> create(@Valid @RequestBody VendedorDTO objDTO) {
        Vendedor newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> update(@PathVariable Integer id, @Valid @RequestBody VendedorDTO objDTO) {
        Vendedor obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VendedorDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
