package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String matricula;
    private String perfil;

    public FuncionarioResponseDTO(Long id,String nome,String cpf,String email,String matricula,String perfil){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.matricula = matricula;
        this.perfil = perfil;
    }



    public static FuncionarioResponseDTO converter(Funcionario funcionario){
        return new FuncionarioResponseDTO(funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEmail(),
                funcionario.getMatricula(),
                funcionario.getPerfil());
    }
}
