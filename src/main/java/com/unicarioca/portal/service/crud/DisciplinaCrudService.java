package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisciplinaCrudService {

    private DisciplinaRepository disciplinaRepository;

    public Disciplina getDisciplinaById(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId).orElse(null);
    }
}
