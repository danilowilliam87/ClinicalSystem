package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import io.github.danilowilliam.ClinicalSystem.model.Funcionario;
import io.github.danilowilliam.ClinicalSystem.model.Medico;
import io.github.danilowilliam.ClinicalSystem.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaRequestDTO {
    private Consulta consulta;

    public Consulta converter(){
        return new Consulta(consulta.getMedico(), consulta.getFuncionario(), consulta.getPaciente(),
                consulta.getValor(), consulta.getFormaPagamento(),consulta.getInformacoesAdicionais(),
                consulta.getSituacao(),consulta.getDataMarcacao(),consulta.getDataConsulta());
    }
}
