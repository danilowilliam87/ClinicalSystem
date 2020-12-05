package io.github.danilowilliam.ClinicalSystem.controller;

import io.github.danilowilliam.ClinicalSystem.dto.request.ConsultaRequestDTO;
import io.github.danilowilliam.ClinicalSystem.dto.response.ConsultaResponseDTO;
import io.github.danilowilliam.ClinicalSystem.model.Consulta;
import io.github.danilowilliam.ClinicalSystem.model.StatusConsulta;
import io.github.danilowilliam.ClinicalSystem.repository.ConsultaRepository;
import io.github.danilowilliam.ClinicalSystem.repository.FuncionarioRepository;
import io.github.danilowilliam.ClinicalSystem.repository.MedicoRepository;
import io.github.danilowilliam.ClinicalSystem.repository.PacienteRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    //paciente tem que estar cadastrado no sistema para marcar consulta

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaResponseDTO salvar(@RequestBody Consulta consulta){
        //realizar a busca do paciente no BD
        pacienteRepository.findByCpfLike(consulta.getPaciente().getCpf())
                .map(paciente -> {
                          consulta.setPaciente(paciente);
                          return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"paciente não encontrado"));

        //realizar a busca do médico no BD
        medicoRepository.findByCrmLike(consulta.getMedico().getCrm())
                .map(medico -> {
                    consulta.setMedico(medico);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        //realizar a busca do funcionário bo BD
        funcionarioRepository.findByCpfLike(consulta.getFuncionario().getCpf())
                .map(funcionario -> {
                    consulta.setFuncionario(funcionario);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //depois de setados todos os objetos na consulta...salvar a consulta
        consulta.setSituacao(StatusConsulta.EM_ESPERA);
        LocalDate hoje = LocalDate.now();
        //data da marcação será gravada no momento que a consulta for salva
        consulta.setDataMarcacao(hoje);
        Consulta marcada = repository.save(consulta);
        return ConsultaResponseDTO.converter(marcada);
    }

    @GetMapping("/busca/{id}")
    @ResponseBody
    public ConsultaResponseDTO busca(@PathVariable Long id){
        return repository
                .findById(id)
                .map(consulta -> {
                    return ConsultaResponseDTO.converter(consulta);
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/lista-todos")
    @ResponseBody
    public List<ConsultaResponseDTO>listar(){
        List<ConsultaResponseDTO>listaTodos = new ArrayList<>();
        repository
                .findAll()
                .stream()
                .forEach(consulta -> {
                    listaTodos.add(ConsultaResponseDTO.converter(consulta));
                });
        return listaTodos;
    }

    @GetMapping("/consultas-medico/")
    @ResponseBody
    public List<ConsultaResponseDTO> listaDeConsultaPorMedico(@RequestParam(required = true) String crm, @RequestParam("dataConsulta") LocalDate dataConsulta){
        List<ConsultaResponseDTO>lista = new ArrayList<>();
        repository
                .buscarPorMedicoData(crm,dataConsulta)
                .stream()
                .forEach(consulta -> {
                    lista.add(ConsultaResponseDTO.converter(consulta));
                });

        return lista;
    }

    @GetMapping("/busca-paciente/{cpf}")
    @ResponseBody
    public List<ConsultaResponseDTO>buscaPorPaciente(@PathVariable String cpf){
        List<ConsultaResponseDTO>lista = new ArrayList<>();
        repository
                .buscaPorPaciente(cpf)
                .stream()
                .forEach(consulta -> {
                    lista.add(ConsultaResponseDTO.converter(consulta));
                });
        return lista;
    }

    @PutMapping("/abrir-consulta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrirConsulta(@RequestBody Consulta consulta){
        repository
                .buscaPorPacienteData(consulta.getPaciente().getCpf(),consulta.getDataConsulta())
                .map(consulta1 -> {
                    consulta.setId(consulta1.getId());
                    consulta.setSituacao(StatusConsulta.ABERTA);
                    repository.save(consulta);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/finalizar-consulta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizarConsulta(@RequestBody Consulta consulta){
        repository
                .buscaPorPacienteData(consulta.getPaciente().getCpf(),consulta.getDataConsulta())
                .map(consulta1 -> {
                    consulta.setId(consulta1.getId());
                    consulta.setSituacao(StatusConsulta.FINALIZADA);
                    repository.save(consulta);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/cancelar-consulta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelarConsulta(@RequestBody Consulta consulta){
        repository
                .buscaPorPacienteData(consulta.getPaciente().getCpf(),consulta.getDataConsulta())
                .map(consulta1 -> {
                    consulta.setId(consulta1.getId());
                    consulta.setSituacao(StatusConsulta.CANCELADA);
                    repository.save(consulta);
                    return Void.TYPE;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/consulta-atual/{crm}")
    @ResponseBody
    public List<ConsultaResponseDTO>consultasDataAtual(@PathVariable("crm") String crm){
        //consultas do dia para ser carregada na tela do médico
        LocalDate hoje = LocalDate.now();
        List<ConsultaResponseDTO>lista = new ArrayList<>();
        //adicionar consultas na lista
        repository
                .buscarPorMedicoData(crm,hoje)
                .stream()
                .forEach(consulta -> {
                    lista.add(ConsultaResponseDTO.converter(consulta));
                });
        return lista;
    }

    @GetMapping("/{crm}")
    @ResponseBody
    public List<ConsultaResponseDTO> consultaPorMedico(@PathVariable String crm){
        List<ConsultaResponseDTO>lista = new ArrayList<>();
        repository
                .findConsultaByMedico(crm)
                .stream()
                .forEach(consulta -> {
                    lista.add(ConsultaResponseDTO.converter(consulta));
                });

        return lista;
    }

    @GetMapping("/data")
    @ResponseBody
    public List<ConsultaResponseDTO> consultaDatas(@RequestParam("data_consulta") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate dataConsulta){
        List<ConsultaResponseDTO>lista = new ArrayList<>();
        repository.
                findAllByData(dataConsulta)
                .stream()
                .forEach(consulta -> {
                    lista.add(ConsultaResponseDTO.converter(consulta));
                });
        return lista;
    }
}
