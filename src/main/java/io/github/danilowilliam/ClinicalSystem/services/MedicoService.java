package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Medico;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public List<Medico>listarTodos(){
        return repository.findAll();
    }
    public Optional<Medico>buscarPoId(Long id){
        return repository.findById(id);
    }
    public Medico salvar(Medico medico){
        return repository.save(medico);
    }
    public boolean atualizar(Long id, Medico medico){
        Optional<Medico>busca = repository.findById(id);
        if (busca.isPresent()){
            Medico novo = busca.get();
            novo.setNome(medico.getNome());
            novo.setCpf(medico.getCpf());
            novo.setCrm(medico.getCrm());
            novo.setEspecialidade(medico.getEspecialidade());
            novo.setSenha(medico.getSenha());
            novo.setConsultas(medico.getConsultas());
            return true;
        } else {
            return false;
        }
    }

    public boolean deletar(Long id){
        Optional<Medico>medico = repository.findById(id);
        medico.ifPresent(busca -> repository.delete(busca));
        return true;
    }

}
