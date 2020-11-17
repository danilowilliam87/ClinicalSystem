package io.github.danilowilliam.ClinicalSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Funcionario funcionario;
    @ManyToOne
    private Dependente dependente;
    private Double valor;
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @Column(name = "informacoes_adicionais")
    private String informacoesAdicionais;
    private String situacao;
    @Column(name = "data_marcacao")
    private LocalDate dataMarcacao;
    @Column(name = "data_consulta")
    private LocalDate dataConsulta;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDate dataConsulta, String informacoesAdicionais){
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Consulta(Long id, Medico medico, Dependente dependente, LocalDate dataConsulta, String informacoesAdicionais){
        this.id = id;
        this.medico = medico;
        this.dependente = dependente;
        this.dataConsulta = dataConsulta;
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Consulta(String formaPagamento,
                    String informacoesAdicionais,
                    LocalDate dataMarcacao,
                    LocalDate dataConsulta,
                    String situacao){
        this.formaPagamento = formaPagamento;
        this.informacoesAdicionais = informacoesAdicionais;
        this.dataMarcacao = dataMarcacao;
        this.dataConsulta = dataConsulta;
        this.situacao = situacao;

    }

}
