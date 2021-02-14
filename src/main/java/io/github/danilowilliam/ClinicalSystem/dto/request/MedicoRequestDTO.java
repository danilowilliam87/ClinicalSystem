package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Especialidade;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequestDTO {
    private Long id;
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotBlank(message = "campo.cpf.obrigatorio")
    @CPF(message = "campo.cpf.valido")
    private String cpf;
    @NotBlank(message = "{campo.crm.obrigatorio}")
    private String crm;
    @NotBlank(message = "{campo.senha.obrigatorio}")
    private String senha;
    private Especialidade especialidade;

    public Medico converter(){
        return new Medico(nome,cpf,crm,senha,especialidade);
    }
}
