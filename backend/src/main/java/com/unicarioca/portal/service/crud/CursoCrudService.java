package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Curso;
import com.unicarioca.portal.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoCrudService {

    private CursoRepository cursoRepository;

    public Curso getCursoById(Long cursoId) {
        return cursoRepository.findById(cursoId).orElse(null);
    }
}
