package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDependenteRequestDTO {
    @NotBlank
    private String formaPagamento;
    @NotBlank
    private String informacoesAdicionais;
    @NotBlank
    private String situacao;
    @NotBlank
    private LocalDate dataMarcacao;
    @NotBlank
    private LocalDate dataConsulta;

    public Consulta converter(){
        return new Consulta(formaPagamento,
                informacoesAdicionais,
                dataMarcacao,
                dataConsulta,
                situacao);
    }

}
