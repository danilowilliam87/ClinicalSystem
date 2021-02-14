package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.ConvenioRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.ConvenioResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.services.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService service;

    @PostMapping
    @ResponseBody
    public Convenio salvar(@RequestBody ConvenioRequestDTO dto) {
        return service.salvar(dto.converter());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Convenio buscar(@PathVariable Long id) {
        return service.busca(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody ConvenioRequestDTO dto, @PathVariable Long id) {
        service.atualizar(dto.converter(), id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizacaoParcial(@RequestBody ConvenioRequestDTO dto, @PathVariable Long id) {
        service.atualizacaoParcial(dto.converter(), id);
    }

    @GetMapping
    public List<ConvenioResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}
