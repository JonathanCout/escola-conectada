package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorCrudService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }

    public Professor getProfessorById(Long id){
        return professorRepository.findById(id).orElse(null);
    }

    public Professor getProfessorByMatricula(String matricula){
        return professorRepository.findByMatricula(matricula);
    }

    public Professor saveProfessor(Professor professor){
        return professorRepository.save(professor);
    }

    public Professor getProfessor(String cpf, String matricula) {
        if(cpf != null){
            return professorRepository.findByCpf(cpf);
        }else if(matricula != null){
            return professorRepository.findByMatricula(matricula);
        }
        return null;
    }

    public Professor getProfessorByEmail(String email) {
        return professorRepository.findByEmail(email);
    }
}
