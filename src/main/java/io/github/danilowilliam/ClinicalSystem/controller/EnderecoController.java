package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import io.github.danilowilliam.ClinicalSystem.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping("/salvar")
    public ResponseEntity<Endereco>salvar(@RequestBody Endereco endereco){
        service.salvar(endereco);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public List<Endereco>lista(){
        return service.listarTodos();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Endereco>deletar(@PathVariable("id") Long id){
        if (service.deletar(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Endereco>atualizar(@PathVariable("id")Long id, @RequestBody Endereco novo){
        if (service.atualizar(id, novo)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("busca/{id}")
    public ResponseEntity<Endereco> busca(@PathVariable("id") Long id){
        Optional<Endereco>procurado = service.buscaPorId(id);
        if (procurado.isPresent()){
            return new ResponseEntity<Endereco>(procurado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
