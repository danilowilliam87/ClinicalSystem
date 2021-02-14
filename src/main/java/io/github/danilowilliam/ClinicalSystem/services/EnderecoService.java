package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> listarTodos() {
        return repository.findAll();
    }

    public Optional<Endereco> buscaPorId(Long id) {
        return repository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public boolean atualizar(Long id, Endereco endereco) {
        Optional<Endereco> antigo = repository.findById(id);
        if (antigo.isPresent()) {
            Endereco novo = antigo.get();
            novo.setCep(endereco.getCep());
            novo.setLogradouro(endereco.getLogradouro());
            novo.setNumero(endereco.getNumero());
            novo.setCidade(endereco.getCidade());
            novo.setEstado(endereco.getEstado());
            repository.save(novo);
            return true;
        } else {
            return false;
        }
    }

    public boolean deletar(Long id) {
        Optional<Endereco> endereco = repository.findById(id);
        if (endereco.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
