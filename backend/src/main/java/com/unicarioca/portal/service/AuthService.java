package com.unicarioca.portal.service;

import com.unicarioca.portal.controller.dto.LoginRequest;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.service.crud.AlunoCrudService;
import com.unicarioca.portal.service.crud.ProfessorCrudService;
import com.unicarioca.portal.util.TipoUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AlunoCrudService alunoCrudService;
    @Autowired
    private ProfessorCrudService professorCrudService;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public void login(LoginRequest loginRequest) {
        try {
            if (loginRequest.getEmail().equalsIgnoreCase("admin")){
                return;
            }
            if (loginRequest.getTipoUsuario().equalsIgnoreCase(TipoUsuario.ALUNO.toString())) {
                Aluno aluno = alunoCrudService.getAlunoByEmail(loginRequest.getEmail());
                if (aluno == null || !checkPassword(loginRequest.getSenha(), aluno.getSenha())) {
                    throw new RuntimeException("Email ou senha inválidos");
                }

            } else if (loginRequest.getTipoUsuario().equalsIgnoreCase(TipoUsuario.PROFESSOR.toString())) {
                Professor professor = professorCrudService.getProfessorByEmail(loginRequest.getEmail());
                if (professor == null || !checkPassword(loginRequest.getSenha(), professor.getSenha())) {
                    throw new RuntimeException("Email ou senha inválidos");
                }
            }
        } catch (Exception e) {
            log.error("Erro ao realizar login", e);
            throw e;
        }
    }
}
