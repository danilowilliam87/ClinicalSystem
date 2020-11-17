package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Dependente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DependenteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public static DependenteResponseDTO converter(Dependente dependente){
        return new DependenteResponseDTO(dependente.getId(),
                dependente.getNome(),
                dependente.getEmail(),
                dependente.getCpf(),
                dependente.getTelefone());
    }
}
