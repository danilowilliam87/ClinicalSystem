package io.github.danilowilliam.ClinicalSystem.services;

import io.github.danilowilliam.ClinicalSystem.dto.request.ConsultaRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.ConsultaResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import io.github.danilowilliam.ClinicalSystem.model.StatusConsulta;
import io.github.danilowilliam.ClinicalSystem.repository.ConsultaRepository;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    public Consulta marcarConsulta(Consulta consulta){
       consulta.setDataMarcacao(LocalDate.now());
       consulta.setSituacao(StatusConsulta.ABERTA);
       consulta.setFuncionario(funcionarioService.findByCpfLike(consulta.getFuncionario().getCpf()));
       consulta.setMedico(medicoService.busca(consulta.getMedico().getCrm()));
       consulta.setPaciente(pacienteService.buscar(consulta.getPaciente().getCpf()));
       return repository.save(consulta);
    }

    public Consulta cancelar(Long id){
        return repository
                .findById(id)
                .map(consulta -> {
                    consulta.setId(id);
                    consulta.setSituacao(StatusConsulta.CANCELADA);
                    return repository.save(consulta);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public Consulta confirmar(Long id){
        return repository
                .findById(id)
                .map(consulta -> {
                    consulta.setId(id);
                    consulta.setSituacao(StatusConsulta.EM_ESPERA);
                    return repository.save(consulta);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public Consulta darBaixaNaConsulta(Long id){
        return repository
                .findById(id)
                .map(consulta -> {
                    consulta.setId(id);
                    consulta.setSituacao(StatusConsulta.FINALIZADA);
                    return repository.save(consulta);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Consulta> listarPoMedico(String crm){
        return repository.findConsultaByMedico(crm);
    }

    public List<Consulta> listarPorData(LocalDate data){
        return repository.findAllByData(data);
    }

    public List<Consulta>listarPorMedicoEPorData(String crm, LocalDate data){
        return repository.buscarPorMedicoData(crm, data);
    }


}
