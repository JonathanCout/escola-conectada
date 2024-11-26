package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Endereco;
import com.unicarioca.portal.repository.AlunoRepository;
import com.unicarioca.portal.service.mapper.AlunoMapper;
import com.unicarioca.portal.util.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AlunoCrudService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EnderecoCrudService enderecoCrudService;
    @Autowired
    private ParenteCrudService parenteCrudService;
    @Autowired
    private PasswordEncoder passwordEncoderConfig;

    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno getAlunoByMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }
    public Aluno getAlunoByEmail(String email) {
        return alunoRepository.findByEmail(email);
    }

    public Aluno getAlunoByCpf(String cpf) {
        return alunoRepository.findByCpf(cpf);
    }

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

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }

}
