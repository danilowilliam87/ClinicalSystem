package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {
    @Autowired
    EspecialidadeRepository repository;

    public List<Especialidade>listarTodos(){
        return repository.findAll();
    }
    public Optional<Especialidade>buscaPorId(Long id){
        return repository.findById(id);
    }
    public Especialidade salvar(Especialidade especialidade){
        return repository.save(especialidade);
    }
    public boolean atualizar(Long id, Especialidade especialidade){
        Optional<Especialidade>antiga = repository.findById(id);
        if (antiga.isPresent()){
            Especialidade nova = antiga.get();
            nova.setNome(especialidade.getNome());
            repository.save(nova);
            return true;
        } else {
            return false;
        }
    }

    public boolean deletar(Long id){
        Optional<Especialidade> busca = repository.findById(id);
        busca.ifPresent(especialidade -> repository.delete(especialidade));
        return true;
    }
}
