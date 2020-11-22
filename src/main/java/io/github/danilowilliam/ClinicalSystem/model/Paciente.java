package io.github.danilowilliam.ClinicalSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String telefone;
    @Column(name = "data_nascimento", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = false)
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;


    @OneToMany(mappedBy = "paciente")
    private List<Consulta>consultas;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;


    public Paciente(String nome,String email,String cpf,String telefone,LocalDate dataNascimento,Endereco endereco,Convenio convenio){
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.convenio = convenio;
    }

}
