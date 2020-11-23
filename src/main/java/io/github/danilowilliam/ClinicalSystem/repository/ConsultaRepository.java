package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //retorna todas as consultas de um determinado médico
    @Query("from Consulta c where c.medico.crm =:crm")
    public List<Consulta>findConsultaByMedico(@Param("crm") String crm);

    //retorna consultas em uma determinada data
    @Query("from Consulta c where c.dataConsulta =: dataConsulta")
    public Consulta findByData(@Param("dataConsulta")LocalDate dataConsulta);

    public List<Consulta>findByDataLike(LocalDate dataConsulta);

    //retorna uma lista de consultas de um determinado paciente
    @Query("from Consulta c where c.paciente.cpf =: cpf")
    public List<Consulta> buscaPorPaciente(String cpf);

    //retorna consulta marcadas por um determinado funcionário
    @Query("from Consulta c where c.funcionario.cpf =: cpf")
    public List<Consulta>buscaPorFuncionario(@Param("cpf") String cpf);

    //query que retorna uma lista de consultas médicas de acordo com o médico e a data
    @Query("from Consulta c where c.paciente.cpf =: cpf and c.dataConsulta =: dataConsulta")
    public List<Consulta>buscarPorMedicoData(@Param("cpf") String cpf, @Param("dataConsulta") LocalDate dataConsulta);
}
