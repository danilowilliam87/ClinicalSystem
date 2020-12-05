package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Convenio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioRequestDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String tipo;

   public Convenio converter(){
       return new Convenio(nome,tipo);
   }
}
