package com.locap.locadora.resources;

import com.locap.locadora.domain.Fatura;
import com.locap.locadora.domain.dtos.FaturaDTO;
import com.locap.locadora.services.FaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/faturas")
public class FaturaResource {

    private final FaturaService service;

    @GetMapping("/{id}")
    public ResponseEntity<FaturaDTO> findById(@PathVariable Integer id) {
        Fatura obj = service.findById(id);
        return ResponseEntity.ok().body(new FaturaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<FaturaDTO>> findAll() {
        List<Fatura> list = service.findAll();
        List<FaturaDTO> listDTO = list.stream().map(obj -> new FaturaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FaturaDTO> create(@Valid @RequestBody FaturaDTO objDTO) {
        Fatura newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<FaturaDTO> update(@PathVariable Integer id, @Valid @RequestBody FaturaDTO objDTO) {
        Fatura obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new FaturaDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<FaturaDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
