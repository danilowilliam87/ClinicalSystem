package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository repository;

    public List<Convenio>listarTodos(){
        return repository.findAll();
    }

    public Optional<Convenio> buscarPorId(Long id){
        return repository.findById(id);
    }

    public boolean alterar(Long id, Convenio convenioAtualizado){
        Optional<Convenio>convenioAntigo = repository.findById(id);
        if (convenioAntigo.isPresent()){
            Convenio convenio = convenioAntigo.get();
            convenio.setNome(convenioAtualizado.getNome());
            convenio.setTipo(convenioAtualizado.getTipo());
            repository.save(convenio);
            return true;
        } else {
            return false;
        }
    }

    public Convenio salvar(Convenio convenio){
        return repository.save(convenio);
    }

    public boolean deletar(Long id){
        Optional<Convenio>convenio = repository.findById(id);
        if (convenio.isPresent()){
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
