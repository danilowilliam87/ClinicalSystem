package io.github.danilowilliam.ClinicalSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 9)
    private String cep;
    @Column(nullable = false, length = 100)
    private String logradouro;
    @Column(name = "numero_residencia",nullable = false)
    private int numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false,length = 2) //Ex de Estado : BA,SP,RJ,RS...
    private String estado;

    public Endereco(String cep,String logradouro,int numero,String cidade,String estado){
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }


}
