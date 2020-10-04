package io.github.danilowilliam.ClinicalSystem.service;

import io.github.danilowilliam.ClinicalSystem.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

}
