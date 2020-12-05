package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.repository.EspecialidadeRepository;
import io.github.danilowilliam.ClinicalSystem.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository repository;

    @ResponseBody
    @PostMapping("/salvar")
    public Especialidade salvar(@RequestBody @Valid Especialidade especialidade){
        return repository.save(especialidade);
    }

    @GetMapping("/busca/{id}")
    @ResponseBody
    public Especialidade busca(@PathVariable Long id){
        return repository.
                findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/busca-nome/{nome}")
    @ResponseBody
    public Especialidade buscaPorNome(@PathVariable String nome){
        return repository
                .findByNomeLike(nome)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Especialidade>lista(){
        return repository.findAll();
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid Especialidade especialidadeatualizada){
        repository
                .findById(id)
                .map(especialidade1 -> {
                    especialidade1.setId(id);
                    especialidade1.setNome(especialidadeatualizada.getNome());
                    repository.save(especialidade1);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable Long id){
        repository
                .findById(id)
                .map(especialidade -> {
                    repository.delete(especialidade);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
