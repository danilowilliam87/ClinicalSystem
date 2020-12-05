package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.MedicoRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.MedicoResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping("/salvar")
    @ResponseBody
    public MedicoResponseDTO salvar(@RequestBody @Valid MedicoRequestDTO medicoRequestDTO){
        Medico medico =  repository.save(medicoRequestDTO.converter());
        return MedicoResponseDTO.converter(medico);
    }

    @GetMapping("/busca/{id}")
    public MedicoResponseDTO buscarPorId(@PathVariable Long id){
        return repository
                .findById(id)
                .map(medico -> {
                   return MedicoResponseDTO.converter(medico);

                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/busca-nome/{nome}")
    @ResponseBody
    public MedicoResponseDTO buscarPorNome(@PathVariable String nome){
        return repository
                .findByNomeLike(nome)
                .map(medico ->{
                    return MedicoResponseDTO.converter(medico);

                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/busca-crm/{crm}")
    @ResponseBody
    public MedicoResponseDTO buscaPorCrm(@PathVariable  String crm){
        return repository
                .findByCrmLike(crm)
                .map(medico -> {
                    return MedicoResponseDTO.converter(medico);

                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody @Valid MedicoRequestDTO dto, @PathVariable Long id){
        repository
                .findById(id)
                .map(medico -> {
                    medico.setId(id);
                    medico.setNome(dto.getNome());
                    medico.setCpf(dto.getCpf());
                    medico.setSenha(dto.getSenha());
                    medico.setEspecialidade(dto.getEspecialidade());
                    medico.setCrm(dto.getCrm());
                    repository.save(medico);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<MedicoResponseDTO>listar(){
        List<MedicoResponseDTO>listaTodos = new ArrayList<>();
        repository
                .findAll()
                .stream()
                .forEach(medico -> {
                    listaTodos.add(MedicoResponseDTO.converter(medico));
                });

        return listaTodos;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id){
        repository
                .findById(id)
                .map(medico -> {
                    repository.delete(medico);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
