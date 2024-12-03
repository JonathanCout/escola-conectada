package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.service.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final Logger log = LoggerFactory.getLogger(ProfessorController.class);
    @Autowired
    private ProfessorService professorService;

    @GetMapping("list")
    public ResponseEntity<List<ProfessorResponse>> getAllProfessors(){

        log.info("GET /professores/list");
        return ResponseEntity.ok(professorService.getAllProfessors());

    }

    @GetMapping("")
    public ResponseEntity<ProfessorResponse> getProfessor(@RequestParam(required = false) String cpf, @RequestParam(required = false) String matricula, @RequestParam(required = false) String email) {

        log.info("GET /professores/cpf={}&matricula={}&email={}",cpf,matricula, email);
        return ResponseEntity.ok(professorService.getProfessor(cpf, matricula,email));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessor(@PathVariable("id") Long id) {

        log.info("GET /professores/id={}",id);
        return ResponseEntity.ok(professorService.getProfessor(id));

    }

    @PostMapping("")
    public ResponseEntity<ProfessorResponse> saveProfessor(@Validated @RequestBody ProfessorRequest professorRequest) throws Exception {

        log.info("POST /professores/request={}",professorRequest);
        return ResponseEntity.ok(professorService.saveProfessor(professorRequest));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> saveProfessor(@PathVariable  Long id, @RequestBody ProfessorRequest professorRequest) throws Exception {

        log.info("PUT /professores/request={}",professorRequest);
        return ResponseEntity.ok(professorService.updateProfessor(id, professorRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") Long id) {

        log.info("DELETE /professores/id={}",id);
        professorService.deleteProfessor(id);
        return ResponseEntity.ok().build();

    }
}
