package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Optional<Paciente>findByNomeLike(String nome);
    public Optional<Paciente>findByCpfLike(String cpf);
}
