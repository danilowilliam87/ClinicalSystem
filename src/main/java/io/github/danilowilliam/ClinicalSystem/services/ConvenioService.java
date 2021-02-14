package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository repository;

    public Convenio salvar(Convenio convenio) {
        Optional<Convenio> busca = repository.findByNomeLike(convenio.getNome());
        if (busca.isPresent()) {
            busca.map(convenio1 -> {
                convenio.setId(convenio1.getId());
                return repository.save(convenio);
            });
        }
        return repository.save(convenio);
    }

    public Convenio busca(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Convenio busca(String nome) {
        return repository
                .findByNomeLike(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizar(Convenio convenio, Long id) {
        repository
                .findById(id)
                .map(novoConvenio -> {
                    novoConvenio.setId(id);
                    novoConvenio.setNome(convenio.getNome());
                    novoConvenio.setTipo(convenio.getTipo());
                    repository.save(novoConvenio);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletar(Long id) {
        repository.delete(busca(id));
    }

    public List<Convenio> listarTodos() {
        return repository.findAll();
    }
}
