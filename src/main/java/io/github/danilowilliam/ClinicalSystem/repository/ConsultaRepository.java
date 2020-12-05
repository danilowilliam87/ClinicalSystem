package io.github.danilowilliam.ClinicalSystem.repository;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //retorna todas as consultas de um determinado médico
    @Query("from Consulta c where c.medico.crm =:crm")
    public List<Consulta>findConsultaByMedico(@Param("crm") String crm);

    //retorna consultas em uma determinada data
    @Query("from Consulta c where c.dataConsulta =:data")
    public Optional<Consulta> findByData(@Param("data") LocalDate dataConsulta);

    @Query("from Consulta c where c.dataConsulta =: dataConsulta")
    public List<Consulta>buscaPorData(@Param("dataConsulta") LocalDate dataConsulta);

    //retorna uma lista de consultas de um determinado paciente
    @Query("from Consulta c where c.paciente.cpf =: cpf")
    public List<Consulta> buscaPorPaciente(String cpf);

    //retorna consulta marcadas por um determinado funcionário
    @Query("from Consulta c where c.funcionario.cpf =: cpf")
    public List<Consulta>buscaPorFuncionario(@Param("cpf") String cpf);

    //query que retorna uma lista de consultas médicas de acordo com o médico e a data
    @Query("from Consulta c where c.medico.crm=:crm and c.dataConsulta=:dataConsulta")
    public List<Consulta>buscarPorMedicoData(@Param("crm") String crm, @Param("dataConsulta") LocalDate dataConsulta);

    //o documento refere se ao cpf, mudança devido ao erro na execução do método
    @Query("from Consulta c where c.paciente.cpf =:documento and c.dataConsulta =:data")
    public Optional<Consulta>buscaPorPacienteData(@Param("documento") String documento, @Param("data") LocalDate data);

    //retornar todas as consultas de uma data qualquer
    @Query("from Consulta c where c.dataConsulta =:data")
    public List<Consulta>findAllByData(@Param("data") LocalDate data);

}
