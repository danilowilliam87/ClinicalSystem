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

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private EnderecoService enderecoService;

    //método pra teste. não será usado no funcionamento da api
    @PostMapping("/salvar")
    public ResponseEntity<Paciente> salvar(@RequestBody  Paciente paciente,
                                           @RequestBody Convenio convenio,
                                           @RequestBody Endereco endereco){
        Optional<Endereco> novo = enderecoService.buscaPorId(endereco.getId());
        if (novo.isPresent()) {
            paciente.setEndereco(novo.get());
        }
        Optional<Convenio>novoConvenio = convenioService.buscarPorId(convenio.getId());
        if (novoConvenio.isPresent()){
        paciente.setConvenio(novoConvenio.get());
        }
        service.salvar(paciente);
        return new ResponseEntity<>(paciente, HttpStatus.CREATED);
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
}
