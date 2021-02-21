package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.dto.response.MedicoResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;


    public Medico salvar(Medico medico){
        Optional<Medico> busca = repository.findByCrmLike(medico.getCrm());
        if(busca.isPresent()){
            busca.map(atualizarMedico -> {
                atualizarMedico.setNome(medico.getNome());
                atualizarMedico.setCpf(medico.getCpf());
                atualizarMedico.setCrm(medico.getCrm());
                atualizarMedico.setEspecialidade(medico.getEspecialidade());
                return repository.save(atualizarMedico);
            });
        }
        return repository.save(medico);
    }

    public Medico busca(Long id){
        return repository
                .findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Medico busca(String crm){
        return repository
                .findByCrmLike(crm)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizacaoParcial(Medico medico, Long id){
        repository
                .findById(id)
                .map(atualizarMedico -> {
                    atualizarMedico.setId(id);
                    atualizarMedico.setNome(Optional.ofNullable(medico.getNome())
                    .orElse(atualizarMedico.getNome()));
                    atualizarMedico.setCrm(Optional.ofNullable(medico.getCrm())
                    .orElse(atualizarMedico.getCrm()));
                    atualizarMedico.setCpf(Optional.ofNullable(medico.getCrm())
                    .orElse(atualizarMedico.getCpf()));
                    atualizarMedico.setEspecialidade(Optional.ofNullable(medico.getEspecialidade())
                    .orElse(atualizarMedico.getEspecialidade()));
                    atualizarMedico.setSenha(Optional.ofNullable(medico.getSenha())
                    .orElse(atualizarMedico.getSenha()));
                    repository.save(atualizarMedico);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Medico>listar(){
        return repository.findAll();
    }

    public void deletar(Long id){
        repository.delete(busca(id));
    }
}
