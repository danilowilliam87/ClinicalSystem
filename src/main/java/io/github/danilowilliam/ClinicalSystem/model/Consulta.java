package io.github.danilowilliam.ClinicalSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//(cascade = CascadeType.MERGE)
    private Medico medico;

    @ManyToOne//(cascade = CascadeType.MERGE)
    private Paciente paciente;

    @ManyToOne//(cascade = CascadeType.MERGE)
    private Funcionario funcionario;

    private Double valor;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "informacoes_adicionais")
    private String informacoesAdicionais;

    @Enumerated(EnumType.STRING)
    private StatusConsulta situacao;

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
                    StatusConsulta situacao){
        this.formaPagamento = formaPagamento;
        this.informacoesAdicionais = informacoesAdicionais;
        this.dataConsulta = dataConsulta;
        this.situacao = situacao;

    }

    public Consulta(){
    }

    public Consulta(Medico medico, Funcionario funcionario,
                    Paciente paciente, Double valor,
                    String formaPagamento,
                    String informacoesAdicionais,
                    StatusConsulta situacao, LocalDate dataMarcacao,
                    LocalDate dataConsulta){
        this.medico = medico;
        this.funcionario = funcionario;
        this.paciente = paciente;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.informacoesAdicionais = informacoesAdicionais;
        this.situacao = situacao;
        this.dataMarcacao = dataMarcacao;
        this.dataConsulta = dataConsulta;
    }

}
