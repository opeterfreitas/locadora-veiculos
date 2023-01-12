package com.locap.locadora.resources;

import com.locap.locadora.domain.Locacao;
import com.locap.locadora.domain.dtos.LocacaoDTO;
import com.locap.locadora.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locacoes")
public class LocacaoResource {

    @Autowired
    private LocacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoDTO> findById(@PathVariable Integer id) {
        Locacao obj = service.findById(id);
        return ResponseEntity.ok().body(new LocacaoDTO(obj));
    }
}
