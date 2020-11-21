package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.ConvenioRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.ConvenioResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import io.github.danilowilliam.ClinicalSystem.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {


    @Autowired
    private ConvenioRepository repository;

    //refatoração dos métodos
    @GetMapping("{id}")
    @ResponseBody
    public Convenio buscar(@PathVariable Long id){
        //se existir o objeto com o id retorna
        //caso contrário retorará uma exceção
        return repository.findById(id)
                .map(convenio -> {
                    ConvenioResponseDTO.converter(convenio);
                    return convenio;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable Long id){
        repository
                .findById(id)    //busca o convenio
                .map(convenio -> {    //se encontrar .. o map é responsável por executar alguma ação sobre o objeto ou gerar um novo objeto por exemplo
                    repository.delete(convenio);   //caso exista ..ele deletará o objeto
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));  //caso contrário...retornará uma exceção
    }

    @PutMapping("/alterar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Long id,@RequestBody ConvenioRequestDTO convenioAtualizado){
        repository
                .findById(id)  //busca o convenio
                .map(convenio -> {  //se encontrar...atualizará
                       convenio.setNome(convenioAtualizado.getNome());
                       convenio.setTipo(convenioAtualizado.getTipo());
                       return repository.save(convenio);
                }) //se não...lançará um exceção
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @ResponseBody
    @GetMapping("/listagem")
    public List<ConvenioResponseDTO>listagem(){
        List<ConvenioResponseDTO>dtos = new ArrayList<>();
        repository.findAll()
                .stream()
                .forEach(convenio -> dtos.add(ConvenioResponseDTO.converter(convenio)));
        return dtos;
    }


    @ResponseBody
    @PostMapping("/salvar")
    public Convenio salvar(@RequestBody ConvenioRequestDTO requestDTO){
        return repository.save(requestDTO.converter());
    }

    @GetMapping("/busca-por-nome/{nome}")
    @ResponseBody
    public Convenio buscarPorNome(@PathVariable String nome){
        return repository.findByNomeLike(nome)
                .map(convenio -> {
                    ConvenioResponseDTO.converter(convenio);
                    return convenio;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}
