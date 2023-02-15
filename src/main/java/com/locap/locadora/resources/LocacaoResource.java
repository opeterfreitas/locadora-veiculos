package com.locap.locadora.resources;

import com.locap.locadora.domain.Locacao;
import com.locap.locadora.domain.dtos.LocacaoDTO;
import com.locap.locadora.services.LocacaoService;
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
@RequestMapping("/locacoes")
public class LocacaoResource {

    private final LocacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoDTO> findById(@PathVariable Integer id) {
        Locacao obj = service.findById(id);
        return ResponseEntity.ok().body(new LocacaoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<LocacaoDTO>> findAll() {
        List<Locacao> list = service.findAll();
        List<LocacaoDTO> listDTO = list.stream().map(obj -> new LocacaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/abertos")
    public ResponseEntity<List<LocacaoDTO>> findAllOpen() {
        List<Locacao> list = service.findAllOpen();
        List<LocacaoDTO> listDTO = list.stream().map(obj -> new LocacaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/fechados")
    public ResponseEntity<List<LocacaoDTO>> findAllClose() {
        List<Locacao> list = service.findAllClose();
        List<LocacaoDTO> listDTO = list.stream().map(obj -> new LocacaoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<LocacaoDTO> create(@RequestBody @Valid LocacaoDTO objDTO) {
        Locacao obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocacaoDTO> update(@PathVariable Integer id, @RequestBody @Valid LocacaoDTO objDTO) {
        Locacao newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new LocacaoDTO(newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LocacaoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
