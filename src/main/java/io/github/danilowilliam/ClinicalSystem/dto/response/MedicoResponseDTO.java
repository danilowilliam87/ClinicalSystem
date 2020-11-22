package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoResponseDTO {
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;

    public static MedicoResponseDTO converter(Medico medico){
        return new MedicoResponseDTO(medico.getId(),medico.getNome(),
                                     medico.getCrm(),
                                      medico.getEspecialidade().getNome());
    }
}
