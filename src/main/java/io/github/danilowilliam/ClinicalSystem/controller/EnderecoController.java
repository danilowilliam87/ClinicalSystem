package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.EnderecoRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.EnderecoResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

   @PostMapping("/salvar")
   @ResponseBody
   public Endereco salvar(@RequestBody @Valid EnderecoRequestDTO endereco){
       return repository.save(endereco.converter());
   }

   @GetMapping("/busca/{id}")
   @ResponseBody
   public Endereco buscaPorId(@PathVariable Long id){
       return repository.findById(id)
               .map(endereco -> {
                   EnderecoResponseDTO.converter(endereco);
                   return endereco;
               }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }

   @GetMapping("/busca-cep/{cep}")
   @ResponseBody
   public Endereco buscaPeloCep(@PathVariable String cep){
       return repository.findByCepLike(cep)
               .map(endereco -> {
                   EnderecoResponseDTO.converter(endereco);
                   return endereco;
               }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }


   @GetMapping("/busca-logradouro/{logradouro}")
   @ResponseBody
   public Endereco buscaPeloLogradouro(@PathVariable String logradouro){
       return repository.findByLogradouroLike(logradouro)
               .map(endereco -> {
                   EnderecoResponseDTO.converter(endereco);
                   return endereco;
               }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }


   @ResponseStatus(HttpStatus.NO_CONTENT)
   @PutMapping("/atualizar/{id}")
   public void atualizar(@RequestBody @Valid EnderecoRequestDTO dto, @PathVariable Long id){
        repository.findById(id)
        .map(endereco -> {
            endereco.setCep(dto.getCep());
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setNumero(dto.getNumero());
            endereco.setCidade(dto.getCidade());
            endereco.setEstado(dto.getEstado());
            repository.save(endereco);
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
   }


   @GetMapping("/lista")
   public List<EnderecoResponseDTO>lista(){
       List<EnderecoResponseDTO>listaTodos = new ArrayList<>();
       repository.findAll()
               .stream()
               .forEach(endereco -> {
                   listaTodos.add(EnderecoResponseDTO.converter(endereco));
               });
       return listaTodos;
   }

   @DeleteMapping("/deletar/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deletar(@PathVariable Long id){
       repository.findById(id)
               .map(endereco -> {
                   repository.deleteById(id);
                   return Void.TYPE;
               }).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND)));
   }

}
