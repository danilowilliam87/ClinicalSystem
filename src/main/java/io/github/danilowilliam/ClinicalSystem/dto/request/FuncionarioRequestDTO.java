package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO {
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotBlank(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.valido}")
    private String cpf;
    @NotBlank(message = "{campo.email.obrigatorio}")
    @Email(message = "{campo.email.valido}")
    private String email;
    //criar função para gerar matrícula automaticamente
    private String matricula;
    @NotBlank(message = "{campo.senha.obrigatorio}")
    private String senha;
    private String perfil;

    public Funcionario converter(){
        return new Funcionario(nome,cpf,email,matricula,senha,perfil);
    }
}
