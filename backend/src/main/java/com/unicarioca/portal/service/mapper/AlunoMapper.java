package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Endereco;

import java.util.stream.Collectors;

public class AlunoMapper {

    public static AlunoResponse toDto(Aluno aluno) {
        if (aluno == null) {
            return null;
        }
        AlunoResponse alunoResponse = new AlunoResponse();
        alunoResponse.setId(aluno.getId());
        alunoResponse.setNome(aluno.getNome());
        alunoResponse.setEmail(aluno.getEmail());
        alunoResponse.setCpf(aluno.getCpf());
        alunoResponse.setTelefone(aluno.getTelefone());
        alunoResponse.setEndereco(EnderecoMapper.toDto(aluno.getEndereco()));
        alunoResponse.setMatricula(aluno.getMatricula());
        alunoResponse.setAno(aluno.getAno());
        alunoResponse.setResponsaveis(aluno.getResponsaveis().stream().map(ParenteMapper::toDto).collect(Collectors.toSet()));

        return alunoResponse;
    }

    public static Aluno toEntity(AlunoRequest alunoRequest, Endereco endereco) {
        if (alunoRequest == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.getNome());
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setCpf(alunoRequest.getCpf());
        aluno.setTelefone(alunoRequest.getTelefone());
        aluno.setMatricula(alunoRequest.getMatricula());
        aluno.setEndereco(endereco);
        aluno.setAno(alunoRequest.getAno());
        aluno.setResponsaveis(alunoRequest.getResponsaveis().stream().map(ParenteMapper::toEntity).collect(Collectors.toSet()));
        return aluno;
    }
}
