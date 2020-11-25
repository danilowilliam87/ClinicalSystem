package io.github.danilowilliam.ClinicalSystem.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/*
* Aberta = paciente chegou
* Fechada = paciente ainda não chegou
* Finalizada = paciente realizou a consulta
* Cancelada = paciente cancelou a consulta*/
public enum StatusConsulta {
    @Enumerated(EnumType.STRING)
    ABERTA, FINALIZADA, CANCELADA,EM_ESPERA
}
