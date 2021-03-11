package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.dto.request.PacienteRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.PacienteResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private EnderecoService service;

    public Paciente salvar(Paciente pacienteNovo){
        Optional<Paciente> buscaPaciente = repository.findByCpfLike(pacienteNovo.getCpf());
        if (buscaPaciente.isPresent()){
            buscaPaciente.map(paciente -> {
                pacienteNovo.setId(paciente.getId());
                pacienteNovo.setEndereco(service.busca(pacienteNovo.getEndereco().getId()));
                return repository.save(pacienteNovo);
            });
        }
            pacienteNovo.setEndereco(service.busca(pacienteNovo.getEndereco().getId()));
            return repository.save(pacienteNovo);
    }

    public Paciente buscar(Long id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Paciente buscar(String cpf){
        return repository
                .findByCpfLike(cpf)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizar(Paciente paciente, Long id){
        repository
                .findById(id)
                .map(pacienteAtual -> {
                    paciente.setId(pacienteAtual.getId());
                    repository.save(paciente);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizacaoParcial(Paciente paciente, Long id){
        repository
                .findById(id)
                .map(pacienteAtual -> {
                    pacienteAtual.setNome(Optional.ofNullable(paciente.getNome())
                    .orElse(pacienteAtual.getNome()));
                    pacienteAtual.setCpf(Optional.ofNullable(paciente.getCpf())
                    .orElse(pacienteAtual.getCpf()));
                    pacienteAtual.setEmail(Optional.ofNullable(paciente.getEmail())
                    .orElse(pacienteAtual.getEmail()));
                    pacienteAtual.setEndereco(Optional.ofNullable(paciente.getEndereco())
                    .orElse(pacienteAtual.getEndereco()));
                    pacienteAtual.setConvenio(Optional.ofNullable(paciente.getConvenio())
                    .orElse(pacienteAtual.getConvenio()));
                    pacienteAtual.setTelefone(Optional.ofNullable(paciente.getTelefone())
                    .orElse(pacienteAtual.getTelefone()));
                    pacienteAtual.setDataNascimento(Optional.ofNullable(paciente.getDataNascimento())
                    .orElse(pacienteAtual.getDataNascimento()));
                    repository.save(pacienteAtual);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Paciente> listar(){
        return repository.findAll();
    }

    public void deletar(Long id){
        repository.delete(buscar(id));
    }
}
