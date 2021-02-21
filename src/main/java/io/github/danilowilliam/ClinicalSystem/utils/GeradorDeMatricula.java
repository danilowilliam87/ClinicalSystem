package io.github.danilowilliam.ClinicalSystem.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Data
public class GeradorDeMatricula {

    private static final int LIMITE = 5;

    private StringBuilder matricula;

    private SecureRandom random;

    private int numero;

    public String gerarMatricula(){
        matricula = new StringBuilder();
        random = new SecureRandom();
        for (int i = 0; i < LIMITE; i++) {
            numero = random.nextInt(9);
            matricula.append(numero);
        }
        return matricula.toString();
    }
}
