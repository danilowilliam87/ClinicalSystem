package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String matricula;
    private String senha;
    private String perfil;

    public Funcionario converter(){
        return new Funcionario(nome,cpf,email,matricula,senha,perfil);
    }
}
