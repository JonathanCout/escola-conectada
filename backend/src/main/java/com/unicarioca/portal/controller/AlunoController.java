package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.service.AlunoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final Logger log = LoggerFactory.getLogger(AlunoController.class);
    @Autowired
    private AlunoService alunoService;

    @GetMapping("")
    public ResponseEntity<AlunoResponse> getAlunos(@RequestParam(required = false) String cpf, @RequestParam(required = false) String matricula, @RequestParam(required = false) String email) {

        log.info("GET /alunos?cpf={}&matricula={}&email{}", cpf, matricula, email);
        return ResponseEntity.ok(alunoService.getAluno(cpf, matricula, email));

    }

    @GetMapping("/list")
    public ResponseEntity<List<AlunoResponse>> getAllAlunos(){

        log.info("GET /alunos/list");
        return ResponseEntity.ok(alunoService.getAllAlunos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getAlunoById(@PathVariable("id") Long id) {
        log.info("GET /alunos/id={}",id);
        return ResponseEntity.ok(alunoService.getAlunoById(id));
    }

    @PostMapping("")
    public ResponseEntity<AlunoResponse> saveAluno(@RequestBody AlunoRequest alunoRequest) throws Exception {
        log.info("POST /alunos/request={}",alunoRequest);
        return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") Long id) {

        log.info("DELETE /alunos/id={}",id);
        alunoService.deleteAluno(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateAluno(@PathVariable("id") Long id, @RequestBody AlunoRequest alunoRequest) {

        log.info("PUT /alunos/id={}request={}",id, alunoRequest);
        return ResponseEntity.ok(alunoService.updateAluno(id, alunoRequest));

    }
}
