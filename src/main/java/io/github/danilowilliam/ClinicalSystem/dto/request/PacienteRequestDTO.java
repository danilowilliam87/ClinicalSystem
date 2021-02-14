package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotBlank(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.valido}")
    private String email;
    @NotBlank(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.valido}")
    private String cpf;
    @NotBlank(message = "{campo.telefone.obrigatorio}")
    private String telefone;
    private Endereco endereco;
    @NotBlank(message = "{campo.datanascimento.obrigatorio}")
    private LocalDate dataNascimento;
    private Convenio convenio;

    public Paciente converter(){
        return new Paciente(nome,email,cpf,telefone,dataNascimento,endereco,convenio);
    }

}
