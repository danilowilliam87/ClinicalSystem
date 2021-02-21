package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.FuncionarioRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.FuncionarioResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import io.github.danilowilliam.ClinicalSystem.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    @Autowired
    private FuncionarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioResponseDTO salvar(@RequestBody  FuncionarioRequestDTO dto){
        Funcionario funcionario = service.salvar(dto.converter());
        return  FuncionarioResponseDTO.converter(funcionario);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FuncionarioResponseDTO buscaPorId(@PathVariable Long id){
            Funcionario buscaFuncionario = service.buscar(id);
            return FuncionarioResponseDTO.converter(buscaFuncionario);
         }

    @GetMapping("/cpf/{cpf}")
    @ResponseBody
    public FuncionarioResponseDTO buscaPorCpf(String cpf){
        return FuncionarioResponseDTO.converter(service.findByCpfLike(cpf));
    }



    /*

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@RequestBody @Valid FuncionarioRequestDTO dto,@PathVariable Long id){
        service.atualizar(dto.converter(), id);
    }
    */


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizacaoParcial(@RequestBody Funcionario funcionario, @PathVariable Long id){
        service.atualizacaoParcial( funcionario, id );
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable  Long id){
        service.deletar(id);
    }

    @GetMapping
    @ResponseBody
    public List<FuncionarioResponseDTO> listar(){
        List<FuncionarioResponseDTO>lista = new ArrayList<>();
        service.listar()
                .forEach(funcionario -> {
                    lista.add(FuncionarioResponseDTO.converter(funcionario));
                });
        return  lista;
    }

}
