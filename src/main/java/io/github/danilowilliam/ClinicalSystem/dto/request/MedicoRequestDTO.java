package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequestDTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String crm;
    @NotBlank
    private String senha;
    private Especialidade especialidade;

    public Medico converter(){
        return new Medico(nome,cpf,crm,senha,especialidade);
    }
}
