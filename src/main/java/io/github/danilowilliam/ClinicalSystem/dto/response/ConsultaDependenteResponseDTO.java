package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDependenteResponseDTO {
  private Long id;
  private String medico;
  private String dependente;
  private LocalDate dataConsulta;
  private String informacoesAdicionais;

  public ConsultaDependenteResponseDTO converter(Consulta consulta){
      return new ConsultaDependenteResponseDTO(consulta.getId(), consulta.getMedico().getNome()
                                               , consulta.getDependente().getNome()
                                                ,consulta.getDataConsulta()
                                                ,consulta.getInformacoesAdicionais());
  }
}
