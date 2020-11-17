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
    @OneToOne
    private Especialidade especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta>consultas;


}
