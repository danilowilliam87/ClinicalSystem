package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import io.github.danilowilliam.ClinicalSystem.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService service;

    @Autowired
    private ConvenioRepository repository;
    //refatoração dos métodos
    @GetMapping("{id}")
    @ResponseBody
    public Convenio buscar(@PathVariable Long id){
        //se existir o objeto com o id retorna
        //caso contrário retorará uma exceção
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(Long id){
        repository
                .findById(id)    //busca o convenio
                .map(convenio -> {    //se encontrar .. o map é responsável por executar alguma ação sobre o objeto ou gerar um novo objeto por exemplo
                    repository.delete(convenio);   //caso exista ..ele deletará o objeto
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));  //caso contrário...retornará uma exceção
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(Long id, Convenio convenioAtualizado){
        repository
                .findById(id)  //busca o convenio
                .map(convenio -> {  //se encontrar...atualizará
                    convenioAtualizado.setId(convenio.getId());
                    return repository.save(convenioAtualizado);
                }) //se não...lançará um exceção
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @ResponseBody
    @GetMapping("/listagem")
    public List<Convenio>listagem(){
        return repository.findAll();
    }




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
