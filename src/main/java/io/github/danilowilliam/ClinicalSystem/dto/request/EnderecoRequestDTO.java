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
    @NotBlank(message = "{campo.cep.obrigatorio}")
    private String cep;
    @NotBlank(message = "{campo.logradouro.obrigatorio}")
    private String logradouro;

    private int numero;
    @NotBlank(message = "{campo.cidade.obrigatorio}")
    private String cidade;
    @NotBlank(message = "{campo.estado.obrigatorio}")
    private String estado;

    public Endereco converter(){
        return new Endereco(cep,logradouro,numero,cidade,estado);
    }
}
