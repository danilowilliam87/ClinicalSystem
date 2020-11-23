package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaResponseDTO {
    private Long id;
    private String nomeMedico;
    private String nomePaciente;
    private LocalDate dataConsulta;
    private String informacoesAdicionais;

    public static ConsultaResponseDTO converter(Consulta consulta){
        return new ConsultaResponseDTO(consulta.getId(),
                consulta.getMedico().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getDataConsulta(),
                consulta.getInformacoesAdicionais());
    }
}
