package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.FuncionarioRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.FuncionarioResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponseDTO salvar(@RequestBody FuncionarioRequestDTO dto){
        Funcionario funcionario = repository.save(dto.converter());
        return  FuncionarioResponseDTO.converter(funcionario);
    }

    @GetMapping("/busca/{id}")
    @ResponseBody
    public FuncionarioResponseDTO buscaPorId(@PathVariable Long id){
        return repository
                .findById(id)
                .map(funcionario -> {
                    return FuncionarioResponseDTO.converter(funcionario);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/busca-nome/{nome}")
    @ResponseBody
    public FuncionarioResponseDTO buscaPorNome(@PathVariable String nome){
        return repository
                .findByNomeLike(nome)
                .map(funcionario -> {
                   return FuncionarioResponseDTO.converter(funcionario);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/busca-cpf/{cpf}")
    @ResponseBody
    public FuncionarioResponseDTO buscaPorCpf(@PathVariable String cpf){
        return repository
                .findByCpfLike(cpf)
                .map(funcionario -> {
                    return FuncionarioResponseDTO.converter(funcionario);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/busca-matricula/{matricula}")
    public FuncionarioResponseDTO buscaPorMatricula(@PathVariable String matricula){
        return repository
                .findByMatriculaLike(matricula)
                .map(funcionario -> {
                    return FuncionarioResponseDTO.converter(funcionario);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody FuncionarioRequestDTO dto,@PathVariable Long id){
        repository
                .findById(id)
                .map(funcionario -> {
                    funcionario.setId(id);
                    funcionario.setNome(dto.getNome());
                    funcionario.setCpf(dto.getCpf());
                    funcionario.setEmail(dto.getEmail());
                    funcionario.setPerfil(dto.getPerfil());
                    repository.save(funcionario);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable  Long id){
        repository
                .findById(id)
                .map(funcionario -> {
                    repository.deleteById(id);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<FuncionarioResponseDTO>listarTodos(){
        List<FuncionarioResponseDTO>lista = new ArrayList<>();
        repository
                .findAll()
                .stream()
                .forEach(funcionario -> {
                     lista.add(FuncionarioResponseDTO.converter(funcionario));
                });
        return lista;
    }
}
