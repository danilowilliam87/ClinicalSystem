package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.PacienteRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.PacienteResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ConvenioRepository convenioRepository;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteResponseDTO salvar(@RequestBody PacienteRequestDTO dto){
        //busca endereço para vincular ao paciente
        enderecoRepository
                .findByCepLike(dto.getEndereco().getCep())
                .map(endereco -> {
                    dto.setEndereco(endereco);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //busca convênio para vincular ao paciente
        convenioRepository
                .findByNomeLike(dto.getConvenio().getNome())
                .map(convenio -> {
                    dto.setConvenio(convenio);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Paciente paciente = repository.save(dto.converter());
        return PacienteResponseDTO.converter(paciente);
    }

    @GetMapping("/busca/{id}")
    @ResponseBody
    public PacienteResponseDTO busca(@PathVariable Long id){
       return repository
                .findById(id)
                .map(paciente -> {
                    return PacienteResponseDTO.converter(paciente);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/busca-nome/{nome}")
    @ResponseBody
    public PacienteResponseDTO buscaPorNome(@PathVariable String nome){
        return repository
                .findByNomeLike(nome)
                .map(paciente -> {
                    return PacienteResponseDTO.converter(paciente);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/busca-cpf/{cpf}")
    @ResponseBody
    public PacienteResponseDTO buscaPorCpf(@PathVariable String cpf){
        return repository
                .findByCpfLike(cpf)
                .map(paciente -> {
                    return PacienteResponseDTO.converter(paciente);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<PacienteResponseDTO>listarTodos(){
        List<PacienteResponseDTO>lista = new ArrayList<>();
        repository
                .findAll()
                .stream()
                .forEach(paciente -> {
                    lista.add(PacienteResponseDTO.converter(paciente));
                });

        return lista;
    }


    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  atualizar(@RequestBody PacienteRequestDTO dto, @PathVariable Long id){
        repository
                .findById(id)
                .map(paciente ->  {
                    paciente.setId(id);
                    paciente.setNome(dto.getNome());
                    paciente.setEmail(dto.getEmail());
                    paciente.setTelefone(dto.getTelefone());
                    paciente.setDataNascimento(dto.getDataNascimento());
                    paciente.setEndereco(dto.getEndereco());
                    paciente.setConvenio(dto.getConvenio());
                    repository.save(paciente);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        repository
                .findById(id)
                .map(paciente -> {
                    repository.delete(paciente);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
