package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import io.github.danilowilliam.ClinicalSystem.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService service;

    @Autowired
    ConvenioRepository repository;
    //GET
    @GetMapping("/{id}")
    public ResponseEntity<Convenio> getById(@PathVariable("id") Long id){
       Optional <Convenio> busca = service.buscarPorId(id);
        //se o objeto for encontrado...
        if (busca.isPresent())
            return new ResponseEntity<Convenio>(busca.get(), HttpStatus.OK);
        else {
    	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    //POST
    @PostMapping
     public ResponseEntity<Convenio>salvar(@RequestBody Convenio convenio){
        service.salvar(convenio);
        return new ResponseEntity<>(convenio, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Convenio>atualizar(@PathVariable("id") Long id, @RequestBody Convenio convenio){
        if(service.alterar(id, convenio)) {
            return new ResponseEntity<Convenio>(HttpStatus.OK);
        } else{
            return new ResponseEntity<Convenio>(HttpStatus.NOT_FOUND);
        }
    }


    //DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Convenio>deletar(@PathVariable("id") Long id){
        if(service.deletar(id)){
            return new ResponseEntity<Convenio>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Convenio>(HttpStatus.NOT_MODIFIED);
        }
    }

    //LIST
    @GetMapping("/lista")
    public List<Convenio> listar(){
        return service.listarTodos();
    }



}
