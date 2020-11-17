package io.github.danilowilliam.ClinicalSystem.dto.response;

import io.github.danilowilliam.ClinicalSystem.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDTO {
    private Long id;
    private String cep;
    private String logradouro;
    private int numero;
    private String cidade;
    private String estado;

    public static EnderecoResponseDTO converter(Endereco endereco){
        return new EnderecoResponseDTO(endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado());
    }
}
