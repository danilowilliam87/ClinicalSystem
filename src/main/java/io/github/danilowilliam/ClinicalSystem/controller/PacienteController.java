package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import io.github.danilowilliam.ClinicalSystem.service.ConvenioService;
import io.github.danilowilliam.ClinicalSystem.service.EnderecoService;
import io.github.danilowilliam.ClinicalSystem.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService service;


    @GetMapping("/busca/{id}")
    public ResponseEntity<Paciente> buscaPeloId(@PathVariable("id") Long id){
        Optional<Paciente>busca = service.buscaPorId(id);
        if (busca.isPresent()){
            return new ResponseEntity<Paciente>(busca.get(), HttpStatus.FOUND);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    //retorna todos os pacientes
    @GetMapping("/lista")
    public List<Paciente>lista(){
        return service.listarTodos();
    }

    //primeiro : passei apenas o objeto paciente comno parâmetro
    //segundo : na classe paciente, coloque a estrategia para salvar o convenio e o endereco antes de persistir
    //o paciente
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Paciente> save(@RequestBody Paciente paciente){
        service.salvar(paciente);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Paciente>alterar(@RequestBody Paciente pacienteUpdate, @PathVariable("id")Long id){
         boolean ok = service.atualizar(id, pacienteUpdate);
         if (ok){
             return new ResponseEntity<>(pacienteUpdate, HttpStatus.OK);
         } else{
             return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
         }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Paciente>deletar(@PathVariable("id")Long id){
        boolean ok = service.deletar(id);
        if (ok){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}
