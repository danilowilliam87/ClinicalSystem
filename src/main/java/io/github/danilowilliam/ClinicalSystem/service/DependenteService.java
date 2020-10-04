package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.model.Dependente;
import io.github.danilowilliam.ClinicalSystem.repository.DependenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    private DependenteRepository repository;

    public List<Dependente> listarTodos(){
        return repository.findAll();
    }
    public Optional<Dependente>buscaPorId(Long id){
        return repository.findById(id);
    }
    public Dependente salvar(Dependente dependente){
        return repository.save(dependente);
    }
    public boolean atualizar(Long id, Dependente dependente){
        Optional<Dependente>antigo = repository.findById(id);
        if (antigo.isPresent()){
            Dependente novo = antigo.get();
            novo.setNome(dependente.getNome());
            novo.setCpf(dependente.getCpf());
            novo.setEmail(dependente.getEmail());
            novo.setEndereco(dependente.getEndereco());
            novo.setTelefone(dependente.getTelefone());
            novo.setConsultas(dependente.getConsultas());
            novo.setPaciente(dependente.getPaciente());
            return true;
        } else {
            return false;
        }
    }

    public boolean deletar(Long id){
        Optional<Dependente>busca = repository.findById(id);
        busca.ifPresent(resultado -> repository.delete(resultado));
        return true;
    }
}
