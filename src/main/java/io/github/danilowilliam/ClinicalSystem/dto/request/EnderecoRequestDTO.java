package io.github.danilowilliam.ClinicalSystem.dto.request;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequestDTO {
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private int numero;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;

    public Endereco converter(){
        return new Endereco(cep,logradouro,numero,cidade,estado);
    }
}
