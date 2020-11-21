package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
  public Optional<Convenio>findByNomeLike(String nome);
}
