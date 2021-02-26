package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.PacienteRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.PacienteResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import io.github.danilowilliam.ClinicalSystem.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {



    @Autowired
    private PacienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO salvar(@RequestBody PacienteRequestDTO dto){
        return PacienteResponseDTO.converter(service.salvar(dto.converter()));
    }

    @GetMapping("/{cpf}")
    @ResponseBody
    public PacienteResponseDTO buscar(@PathVariable String cpf){
        return PacienteResponseDTO.converter(service.buscar(cpf));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody PacienteRequestDTO dto, @PathVariable Long id){
        service.atualizar(dto.converter(), id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizacaoParcial(@RequestBody PacienteRequestDTO dto, @PathVariable Long id){
        service.atualizacaoParcial(dto.converter(), id);
    }

    @GetMapping
    @ResponseBody
    public List<PacienteResponseDTO>listar(){
        List<PacienteResponseDTO> lista = new ArrayList<>();
        service.listar()
                .forEach(paciente -> {
                    lista.add(PacienteResponseDTO.converter(paciente));
                });
        return lista;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

}
