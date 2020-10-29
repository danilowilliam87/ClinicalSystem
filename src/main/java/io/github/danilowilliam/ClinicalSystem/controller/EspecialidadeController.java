package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;

    @GetMapping("/lista")
    public List<Especialidade>listar(){
        return service.listarTodos();
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<Especialidade>buscaPorId(@PathVariable("id")Long id){
        Optional<Especialidade>procurado = service.buscaPorId(id);
        if (procurado.isPresent()){
            return new ResponseEntity<Especialidade>(procurado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Especialidade>atualizar(@PathVariable("id") Long id,
                                                  @RequestBody Especialidade especialidade){
        if(service.atualizar(id, especialidade)){
            return new ResponseEntity<Especialidade>(especialidade,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/salvar")
    @ResponseBody
    public Especialidade salvar(@RequestBody Especialidade especialidade){
        return service.salvar(especialidade);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Especialidade>deletar(@PathVariable("id")Long id){
        if (service.deletar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
