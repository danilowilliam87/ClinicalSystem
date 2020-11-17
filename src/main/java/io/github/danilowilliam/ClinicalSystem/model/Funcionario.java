package io.github.danilowilliam.ClinicalSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.function.Function;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String matricula;
    private String senha;
    private String perfil;

    @OneToMany(mappedBy = "funcionario")
    private List<Consulta>consultas;
    public Funcionario(String nome,String cpf,String email,String matricula,String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.matricula = matricula;
        this.senha = senha;
    }

    public Funcionario(String nome,String cpf,String email,String matricula,String senha,String perfil){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.matricula = matricula;
        this.senha = senha;
        this.perfil = perfil;
    }

}
