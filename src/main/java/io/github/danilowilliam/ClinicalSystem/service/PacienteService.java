package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<Paciente>listarTodos(){
        return repository.findAll();
    }
    public Optional<Paciente>buscaPorId(Long id){
        return repository.findById(id);
    }
    public Paciente salvar(Paciente paciente){
        return repository.save(paciente);
    }

    public boolean atualizar(Long id, Paciente paciente){
        Optional<Paciente>antigo = repository.findById(id);
        if(antigo.isPresent()){
            Paciente novo = antigo.get();
            novo.setNome(paciente.getNome());
            novo.setCpf(paciente.getCpf());
            novo.setEmail(paciente.getEmail());
            novo.setDataNascimento(paciente.getDataNascimento());
            novo.setTelefone(paciente.getTelefone());
            novo.setConvenio(paciente.getConvenio());
            novo.setConsultas(paciente.getConsultas());
            novo.setEndereco(paciente.getEndereco());
            repository.save(novo);
            return true;
        }else {
            return false;
        }
    }

    public boolean deletar(Long id){
        Optional<Paciente>busca = repository.findById(id);
        busca.ifPresent(resultado -> repository.delete(resultado));
        return true;
    }
}
