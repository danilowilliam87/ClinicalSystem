package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco salvar(Endereco endereco) {
        Optional<Endereco> busca = repository.findByCepLike(endereco.getCep());
        if (busca.isPresent()) {
            busca.map(endereco1 -> {
                endereco.setId(endereco1.getId());
                return repository.save(endereco);
            });
        }
        return repository.save(endereco);
    }

    public Endereco busca(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "endereço não encontrado"));
    }

    public Endereco busca(String cep) {
        return repository
                .findByCepLike(cep)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "endereço não encontrado"));
    }

    public void atualizar(Endereco endereco, Long id) {
        repository
                .findById(id)
                .map(endereco1 -> {
                    endereco.setId(endereco1.getId());
                    return repository.save(endereco);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "endereço não encontrado"));
    }

    public void atualizacaoParcial(Endereco endereco, Long id) {
        repository
                .findById(id)
                .map(endereco1 -> {
                    endereco1.setCep(Optional.ofNullable(endereco.getCep())
                            .orElse(endereco1.getCep()));
                    endereco1.setCidade(Optional.ofNullable(endereco.getCidade())
                            .orElse(endereco1.getCidade()));
                    endereco1.setNumero(Optional.ofNullable(endereco.getNumero())
                            .orElse(endereco1.getNumero()));
                    endereco1.setEstado(Optional.ofNullable(endereco.getEstado())
                            .orElse(endereco1.getEstado()));
                    endereco1.setLogradouro(Optional.ofNullable(endereco.getLogradouro())
                            .orElse(endereco1.getLogradouro()));
                    endereco1.setId(id);
                    return repository.save(endereco1);

                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "endereço não encontrado"));
    }

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.delete(busca(id));
    }

}
