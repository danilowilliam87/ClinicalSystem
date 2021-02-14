package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.repository.EspecialidadeRepository;
import io.github.danilowilliam.ClinicalSystem.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidade salvar(@RequestBody Especialidade especialidade) {
        return service.salvar(especialidade);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Especialidade busca(@PathVariable Long id) {
        return service.busca(id);
    }

    @GetMapping("/nome/{nome}")
    @ResponseBody
    public Especialidade busca(@PathVariable String nome) {
        return service.busca(nome);
    }

    @GetMapping
    @ResponseBody
    public List<Especialidade> lista() {
        return service.listar();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody Especialidade especialidade, @PathVariable Long id) {
        service.atualizar(especialidade, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
