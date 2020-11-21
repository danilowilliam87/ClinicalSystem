package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    public Optional<Especialidade>findByNomeLike(String nome);
}
