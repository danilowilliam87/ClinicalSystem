package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Dependente;
import io.github.danilowilliam.ClinicalSystem.service.DependenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dependente")
public class DependenteController {

    @Autowired
    DependenteService service;

    //listar todos os dependentes
    @GetMapping("/lista")
    @ResponseBody
    public List<Dependente>listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dependente>findById(@PathVariable("id")Long id){
        Optional<Dependente>procurado = service.buscaPorId(id);
        if (procurado.isPresent()){
            return new ResponseEntity<Dependente>(procurado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}
