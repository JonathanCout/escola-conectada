package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Disciplina;
import com.unicarioca.portal.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaCrudService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina getDisciplinaById(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId).orElse(null);
    }
}
