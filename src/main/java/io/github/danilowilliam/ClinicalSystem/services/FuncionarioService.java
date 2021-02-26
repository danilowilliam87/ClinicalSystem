package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import io.github.danilowilliam.ClinicalSystem.utils.GeradorDeMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private GeradorDeMatricula matricula;

    //se o funcionário existir ...atualiza ..se não , salva(não é premitido cadastros repetidos)
    public Funcionario salvar(Funcionario funcionarioNovo){
        Optional<Funcionario> buscaFuncionario = repository
                .findByCpfLike(funcionarioNovo.getCpf());
        if (buscaFuncionario.isPresent()){
            buscaFuncionario.map(funcionarioAtual -> {
               funcionarioNovo.setId(funcionarioAtual.getId());
                return repository.save(funcionarioNovo);
            });

        }
        funcionarioNovo.setPerfil("USER");
        funcionarioNovo.setMatricula(matricula.gerarMatricula());
        return repository.save(funcionarioNovo);
    }

    public void atualizar(Funcionario funcionario, Long id){
        repository
                .findById(id)
                .map(funcionarioParaAtualizar->{
                    funcionario.setId(funcionarioParaAtualizar.getId());
                    repository.save(funcionario);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



    public Funcionario buscar(Long id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletar(Long id){
        repository
                .delete(buscar(id));
    }

    public List<Funcionario> listar(){
        return repository
                .findAll();
    }

    public Funcionario findByCpfLike(String cpf){
        return repository
                .findByCpfLike(cpf)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizacaoParcial(Funcionario funcionario, Long id){
        repository
                .findById(id)
                .map(funcionarioParaAtualizar->{
                    funcionarioParaAtualizar.setId(id);

                    funcionarioParaAtualizar.setNome(Optional.ofNullable(funcionario.getNome())
                    .orElse(funcionarioParaAtualizar.getNome()));

                    funcionarioParaAtualizar.setSenha(Optional.ofNullable(funcionario.getSenha())
                    .orElse(funcionarioParaAtualizar.getSenha()));

                    funcionarioParaAtualizar.setEmail(Optional.ofNullable(funcionario.getEmail())
                    .orElse(funcionarioParaAtualizar.getEmail()));

                    funcionarioParaAtualizar.setPerfil(Optional.ofNullable(funcionario.getPerfil())
                    .orElse(funcionarioParaAtualizar.getPerfil()));

                    return repository.save(funcionarioParaAtualizar);

                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
