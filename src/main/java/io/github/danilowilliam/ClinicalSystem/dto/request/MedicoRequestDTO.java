package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequestDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String crm;
    private String senha;
    private Especialidade especialidade;

    public Medico converter(){
        return new Medico(nome,cpf,crm,senha,especialidade);
    }
}
