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
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;
    @NotBlank(message = "{campo.tipo.obrigatorio}")
    private String tipo;

   public Convenio converter(){
       return new Convenio(nome,tipo);
   }
}
