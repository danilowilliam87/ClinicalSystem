package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.EnderecoRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.EnderecoResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import io.github.danilowilliam.ClinicalSystem.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody Endereco endereco) {
        return service.salvar(endereco);
    }

    @GetMapping("/{id}")
    public Endereco busca(@PathVariable Long id) {
        return service.busca(id);
    }

    @GetMapping("/cep/{cep}")
    public Endereco busca(@PathVariable String cep) {
        return service.busca(cep);
    }

    @PutMapping
    public void atualizar(@RequestBody Endereco endereco, @PathVariable Long id) {
        service.atualizar(endereco, id);
    }

    @GetMapping
    public List<Endereco> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PatchMapping("/{id}")
    public void atualizacaoParcial(@RequestBody  Endereco endereco, @PathVariable Long id){
        service.atualizacaoParcial(endereco, id);
    }
}
