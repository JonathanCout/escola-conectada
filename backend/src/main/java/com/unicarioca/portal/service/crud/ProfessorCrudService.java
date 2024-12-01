package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorCrudService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessor(String cpf, String matricula, String email) {
        if(cpf != null){
            return professorRepository.findByCpf(cpf);
        }else if(matricula != null){
            return getProfessorByMatricula(matricula);
        }else if (email !=null){
            return getProfessorByEmail(email);
        }
        return null;
    }

    public Professor getProfessorByEmail(String email) {
        return professorRepository.findByEmail(email);
    }

    public Professor getProfessorByMatricula(String matricula){
        return professorRepository.findByMatricula(matricula);
    }

    public Professor getProfessorById(Long id){
        return professorRepository.findById(id).orElse(null);
    }

    public Professor saveProfessor(Professor professor){
        return professorRepository.save(professor);
    }



    public void deleteProfessor(Long id){
        professorRepository.deleteById(id);
    }


}
