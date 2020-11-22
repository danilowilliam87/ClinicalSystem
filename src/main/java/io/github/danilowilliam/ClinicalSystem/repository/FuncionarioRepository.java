package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public Optional<Funcionario>findByNomeLike(String nome);
    public Optional<Funcionario>findByCpfLike(String cpf);
    public Optional<Funcionario>findByMatriculaLike(String matricula);
}
