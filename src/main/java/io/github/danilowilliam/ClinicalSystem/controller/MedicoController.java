package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.MedicoRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.MedicoResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import io.github.danilowilliam.ClinicalSystem.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoResponseDTO salvar(@RequestBody MedicoRequestDTO dto){
        return MedicoResponseDTO.converter(service.salvar(dto.converter()));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MedicoResponseDTO buscar(@PathVariable Long id){
        return MedicoResponseDTO.converter(service.busca(id));
    }

    @GetMapping("/crm/{crm}")
    @ResponseBody
    public MedicoResponseDTO buscaPorCrm(@PathVariable String crm){
        return MedicoResponseDTO.converter(service.busca(crm));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody MedicoRequestDTO dto){
        service.atualizacaoParcial(dto.converter(), id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @GetMapping
    @ResponseBody
    public List<MedicoResponseDTO> listar(){
        List<MedicoResponseDTO> lista = new ArrayList<>();
        service.listar()
                .forEach(medico -> {
                    lista.add(MedicoResponseDTO.converter(medico));
                });
        return lista;
    }
}
