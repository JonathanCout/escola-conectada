package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import com.unicarioca.portal.service.crud.TurmaCrudService;
import com.unicarioca.portal.service.mapper.TurmaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaCrudService turmaCrudService;

    public List<TurmaResponse> getAllTurmas() {
        return turmaCrudService.getAllTurmas().stream().map(TurmaMapper::toDto).toList();
    }

    public TurmaResponse getTurma(String codigo, String nome) {
        if (codigo != null) {
            return TurmaMapper.toDto(turmaCrudService.getTurmaByCodigo(codigo));
        } else if (nome != null) {
            return TurmaMapper.toDto(turmaCrudService.getTurmaByNome(nome));
        }
        return null;
    }

    public TurmaResponse getTurma(Long id) {
        return TurmaMapper.toDto(turmaCrudService.getTurmaById(id));
    }

    public TurmaResponse saveTurma(TurmaRequest turmaRequest) {
        return TurmaMapper.toDto(turmaCrudService.saveTurma(TurmaMapper.toEntity(turmaRequest)));
    }

    public void deleteTurma(Long id) {
        turmaCrudService.deleteTurmaById(id);
    }

    public TurmaResponse updateTurma(Long id, TurmaRequest turmaRequest) {
        return TurmaMapper.toDto(turmaCrudService.updateTurma(id, turmaRequest));
    }

}
