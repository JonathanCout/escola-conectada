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
        try{
            log.info("GET /professores/list");
            return ResponseEntity.ok(professorService.getAllProfessors());
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<ProfessorResponse> getProfessor(@RequestParam(required = false) String cpf, @RequestParam(required = false) String matricula, @RequestParam(required = false) String email) {
        try{
            log.info("GET /professores/cpf={}&matricula={}&email={}",cpf,matricula, email);
            return ResponseEntity.ok(professorService.getProfessor(cpf, matricula,email));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessor(@PathVariable("id") Long id) {
        try{
            log.info("GET /professores/id={}",id);
            return ResponseEntity.ok(professorService.getProfessor(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ProfessorResponse> saveProfessor(@Validated @RequestBody ProfessorRequest professorRequest) {
        try{
            log.info("POST /professores/request={}",professorRequest);
            return ResponseEntity.ok(professorService.saveProfessor(professorRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /professores/id={}",id);
            professorService.deleteProfessor(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
