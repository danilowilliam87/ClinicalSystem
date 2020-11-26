package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import io.github.danilowilliam.ClinicalSystem.model.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogConsultaResponseDTO {
    private Long consultaId;
    private String medico;
    private String paciente;
    private LocalDate dataConsulta;
    private LocalDate dataMarcacao;
    private StatusConsulta situacao;

    public static LogConsultaResponseDTO converter(Consulta consulta){
        return new LogConsultaResponseDTO(consulta.getId(),
                consulta.getMedico().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getDataConsulta(),
                consulta.getDataMarcacao(),
                consulta.getSituacao());
    }
}
