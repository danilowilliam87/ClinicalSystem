package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {
    @Autowired
    EspecialidadeRepository repository;

    public Especialidade salvar(Especialidade especialidadeNova) {
        Optional<Especialidade> busca = repository.findByNomeLike(especialidadeNova.getNome());
        if (busca.isPresent()) {
            busca.map(especialidadeAtual -> {
                especialidadeNova.setId(especialidadeAtual.getId());
                return repository.save(especialidadeNova);
            });
        }
        return repository.save(especialidadeNova);
    }

    public Especialidade busca(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "especialidade não localizada :("));
    }

    public Especialidade busca(String nome) {
        return repository
                .findByNomeLike(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "especialidade não localizada :("));
    }

    public void atualizar(Especialidade especialidade, Long id) {
        repository
                .findById(id)
                .map(especialidade1 -> {
                    especialidade1.setId(id);
                    especialidade1.setNome(especialidade.getNome());
                    return repository.save(especialidade1);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                       "especialidade não localizada :("));
    }

    public void atualizacaoParcial(Especialidade especialidade, Long id){
        repository
                .findById(id)
                .map(especialidade1 -> {
                   especialidade1.setNome(Optional.ofNullable(especialidade.getNome())
                   .orElse(especialidade1.getNome()));
                   especialidade1.setId(id);
                   return repository.save(especialidade1);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                      "Especialidade não localizada :("));
    }

    public void deletar(Long id){
        repository.delete(busca(id));
    }

    public List<Especialidade> listar(){
        return repository.findAll();
    }
}
