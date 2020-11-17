package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioResponseDTO {
    private Long id;
    private String nome;
    private String senha;

    public static ConvenioResponseDTO converter(Convenio convenio){
        return new ConvenioResponseDTO(convenio.getId(),
                convenio.getNome(),
                convenio.getTipo());
    }
}
