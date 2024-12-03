package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.AlunoTurmaResponse;
import com.unicarioca.portal.entity.AlunoTurma;
import com.unicarioca.portal.service.AlunoTurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos/turmas")
public class AlunoTurmaController {

    @Autowired
    private AlunoTurmaService alunoTurmaService;
    private final Logger log = LoggerFactory.getLogger(AlunoController.class);

    @GetMapping("/list")
    public ResponseEntity<List<AlunoTurmaResponse>> getAllAlunos(){

        log.info("GET /alunos/list");
        return ResponseEntity.ok(alunoTurmaService.getAllByAlunosIds());

    }


    @PostMapping("")
    public ResponseEntity<AlunoTurmaResponse> saveAlunoTurma(@RequestBody AlunoTurma alunoRequest) throws Exception {
        log.info("POST /alunos/turmas/request={}",alunoRequest);
        return ResponseEntity.ok(alunoTurmaService.saveAlunoTurma(alunoRequest));

    }
}
