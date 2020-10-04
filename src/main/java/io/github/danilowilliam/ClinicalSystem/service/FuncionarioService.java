package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> listarTodos(){
        return repository.findAll();
    }
    public Optional<Funcionario>buscaPorId(Long id){
        return repository.findById(id);
    }
    public Funcionario salvar(Funcionario funcionario){
        return repository.save(funcionario);
    }

    public boolean atualizar(Long id, Funcionario funcionario){
        Optional<Funcionario>antigo = repository.findById(id);
        if (antigo.isPresent()){
            Funcionario novo = antigo.get();
            novo.setNome(funcionario.getNome());
            novo.setCpf(funcionario.getCpf());
            novo.setEmail(funcionario.getEmail());
            novo.setMatricula(funcionario.getMatricula());
            novo.setPerfil(funcionario.getPerfil());
            novo.setSenha(funcionario.getSenha());
            return true;
        } else {
            return  false;
        }
    }

    public boolean deletar(Long id){
        Optional<Funcionario>busca = repository.findById(id);
        busca.ifPresent(resultado -> repository.delete(resultado));
        return true;
    }
}
