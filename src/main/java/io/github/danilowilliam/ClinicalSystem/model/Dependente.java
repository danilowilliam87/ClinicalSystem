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
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "dependente")
    private List<Consulta>consultas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;



}
