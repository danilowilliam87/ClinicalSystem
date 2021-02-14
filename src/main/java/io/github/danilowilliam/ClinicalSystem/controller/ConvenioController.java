package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.services.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService service;

    @PostMapping
    @ResponseBody
    public Convenio salvar(@RequestBody Convenio convenio){
        return service.salvar(convenio);
    }

    @GetMapping
    @ResponseBody
    public Convenio buscar( @PathVariable Long id ){
        return service.busca(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( Convenio convenio, Long id){
        service.atualizar(convenio, id);
    }



}
