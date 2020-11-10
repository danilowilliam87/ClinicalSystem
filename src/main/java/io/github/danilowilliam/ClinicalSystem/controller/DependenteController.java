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

    @PostMapping("/salvar")
    public ResponseEntity<Dependente>save(@RequestBody Dependente dependente){
         service.salvar(dependente);
         Dependente dto = new Dependente();
         dto.setNome(dependente.getNome());
         dto.setEmail(dependente.getEmail());
         return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Dependente>alterar(
            @RequestBody Dependente dependente,
            @PathVariable Long id){
        boolean ok = service.atualizar(id,dependente);
        if (ok){
            Dependente dto = new Dependente();
            dto.setNome(dependente.getNome());
            dto.setEmail(dependente.getEmail());
            return new ResponseEntity<Dependente>(dto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Dependente>deletar(@PathVariable Long id){
        boolean delete = service.deletar(id);
        if (delete){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
