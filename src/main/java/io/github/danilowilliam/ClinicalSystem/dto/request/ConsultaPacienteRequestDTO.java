package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPacienteRequestDTO {
    private String formaPagamento;
    private String informacoesAdicionais;
    private String situacao;
    private LocalDate dataConsulta;

    public Consulta converter(){
        return new Consulta(formaPagamento,
                      informacoesAdicionais,
                      dataConsulta,
                      situacao);
    }
}
