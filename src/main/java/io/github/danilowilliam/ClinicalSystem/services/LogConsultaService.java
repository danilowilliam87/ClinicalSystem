package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.LogConsulta;
import io.github.danilowilliam.ClinicalSystem.repository.LogConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LogConsultaService {

    @Autowired
    LogConsultaRepository repository;

    public LogConsulta salvar(LogConsulta logConsulta){
        return repository.save(logConsulta);
    }

    public List<LogConsulta>listarTodos(){
        return repository.findAll();
    }

    public Optional<LogConsulta>buscaPorId(Long id){
        return repository.findById(id);
    }

    public boolean atualizar(Long id, LogConsulta logConsulta){
        Optional<LogConsulta>antigo = repository.findById(id);
        if (antigo.isPresent()){
            LogConsulta novo = antigo.get();
            novo.setId(logConsulta.getId());
            novo.setConsulta(logConsulta.getConsulta());
            novo.setSituacao(logConsulta.getSituacao());
            return true;
        } else {
            return false;
        }
    }

    public boolean deletar(Long id){
        Optional<LogConsulta>busca = repository.findById(id);
        busca.ifPresent(resultado -> repository.delete(resultado));
        return true;
    }
}
