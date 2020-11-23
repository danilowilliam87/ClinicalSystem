package io.github.danilowilliam.ClinicalSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String crm;
    private String senha;
    @OneToOne(cascade = CascadeType.ALL)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico",orphanRemoval = false)
    private List<Consulta>consultas;

    public Medico(List<Consulta>consultas){
        this.consultas = consultas;
    }

    public Medico(String nome,String cpf,String crm,String senha,Especialidade especialidade){
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.crm = crm;
        this.especialidade = especialidade;
    }

}
