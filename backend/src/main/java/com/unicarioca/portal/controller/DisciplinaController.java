package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.DisciplinaResponse;
import com.unicarioca.portal.controller.dto.DisciplinaRequest;
import com.unicarioca.portal.service.DisciplinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final Logger log = LoggerFactory.getLogger(DisciplinaController.class);
    
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("list")
    public ResponseEntity<List<DisciplinaResponse>> getAllDisciplinas(){
        try{
            log.info("GET /disciplinas/list");
            return ResponseEntity.ok(disciplinaService.getAllDisciplinas());
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@RequestParam(required = false) String nome, @RequestParam(required = false) String codigo) {
        try{
            log.info("GET /disciplinas/nome'={}&codigo={}",nome,codigo);
            return ResponseEntity.ok(disciplinaService.getDisciplina(nome, codigo));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathVariable("id") Long id) {
        try{
            log.info("GET /disciplinas/id={}",id);
            return ResponseEntity.ok(disciplinaService.getDisciplina(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<DisciplinaResponse> saveDisciplina(@Validated @RequestBody DisciplinaRequest disciplinaRequest) {
        try{
            log.info("POST /disciplines/request={}",disciplinaRequest);
            return ResponseEntity.ok(disciplinaService.saveDisciplina(disciplinaRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /disciplinas/id={}",id);
            disciplinaService.deleteDisciplina(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
