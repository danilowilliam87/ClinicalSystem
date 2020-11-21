package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Optional<Endereco>findByCepLike(String cep);
    public Optional<Endereco>findByLogradouroLike(String logradouro);
}
