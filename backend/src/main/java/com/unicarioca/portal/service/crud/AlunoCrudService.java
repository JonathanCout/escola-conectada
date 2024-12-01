package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Endereco;
import com.unicarioca.portal.entity.Parente;
import com.unicarioca.portal.repository.AlunoRepository;
import com.unicarioca.portal.service.mapper.AlunoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoCrudService {

    private static final Logger log = LoggerFactory.getLogger(AlunoCrudService.class);
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EnderecoCrudService enderecoCrudService;
    @Autowired
    private ParenteCrudService parenteCrudService;
    @Autowired
    private PasswordEncoder passwordEncoderConfig;

    public List<Aluno> getAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if (aluno == null){
            return aluno;
        }
        aluno.setResponsaveis(getAlunoParentes(aluno.getId()));
        return aluno;

    }

    public Aluno getAlunoByMatricula(String matricula) {
        Aluno aluno = alunoRepository.findByMatricula(matricula);
        if (aluno == null){
            return aluno;
        }
        aluno.setResponsaveis(getAlunoParentes(aluno.getId()));
        return aluno;
    }
    public Aluno getAlunoByEmail(String email) {
        Aluno aluno = alunoRepository.findByEmail(email);
        if (aluno == null){
            return aluno;
        }
        aluno.setResponsaveis(getAlunoParentes(aluno.getId()));
        return aluno;
    }

    public Aluno getAlunoByCpf(String cpf) {
        Aluno aluno = alunoRepository.findByCpf(cpf);
        if (aluno == null){
            return aluno;
        }
        aluno.setResponsaveis(getAlunoParentes(aluno.getId()));
        return aluno;
    }

    public Set<Parente> getAlunoParentes(Long aluno_id){
        return parenteCrudService.getParentesByAluno(aluno_id);
    }

    @Transactional
    public Aluno saveAluno(AlunoRequest alunoRequest) {
        Aluno aluno = alunoRepository.findByCpf(alunoRequest.getCpf());
        if (aluno != null) {
            return aluno;
        }
        aluno = AlunoMapper.toEntity(alunoRequest, enderecoCrudService.saveEndereco(alunoRequest.getEndereco()));

        aluno.setResponsaveis(alunoRequest.getResponsaveis().stream().map(parenteCrudService::saveParente).collect(Collectors.toSet()));
        aluno.setSenha(passwordEncoderConfig.encode(alunoRequest.getSenha()));


        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno updateAluno(Long id, AlunoRequest alunoRequest) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if (aluno == null) {
            return null;
        }

        aluno.setNome(alunoRequest.getNome());
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setCpf(alunoRequest.getCpf());
        aluno.setTelefone(alunoRequest.getTelefone());
        aluno.setMatricula(alunoRequest.getMatricula());
        Endereco endereco = enderecoCrudService.updateEndereco(aluno.getEndereco().getId(), alunoRequest.getEndereco());
        aluno.setEndereco(endereco == null ? aluno.getEndereco() : endereco);

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deleteAluno(Long id) {
        log.info("Removendo laços na tabela parente_aluno");
        alunoRepository.removeParenteJunction(id);
        log.info("Deletando aluno com id {}", id);
        alunoRepository.deleteById(id);
    }

}
