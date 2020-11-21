package io.github.danilowilliam.ClinicalSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
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
    private Double valor;
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @Column(name = "informacoes_adicionais")
    private String informacoesAdicionais;
    private String situacao;
    @Column(name = "data_marcacao",updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
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

    public Consulta(Long id, Medico medico,  LocalDate dataConsulta, String informacoesAdicionais){
        this.id = id;
        this.medico = medico;
        this.dataConsulta = dataConsulta;
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public Consulta(String formaPagamento,
                    String informacoesAdicionais,
                    LocalDate dataConsulta,
                    String situacao){
        this.formaPagamento = formaPagamento;
        this.informacoesAdicionais = informacoesAdicionais;
        this.dataConsulta = dataConsulta;
        this.situacao = situacao;

    }

    public Consulta(){

    }

}
