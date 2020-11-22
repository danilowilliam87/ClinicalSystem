package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private LocalDate dataNascimento;
    private Convenio convenio;

    public Paciente converter(){
        return new Paciente(nome,email,cpf,telefone,dataNascimento,endereco,convenio);
    }

}
