package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    @Email
    private String email;
    //criar função para gerar matrícula automaticamente
    private String matricula;
    @NotBlank
    private String senha;
    private String perfil;

    public Funcionario converter(){
        return new Funcionario(nome,cpf,email,matricula,senha,perfil);
    }
}
