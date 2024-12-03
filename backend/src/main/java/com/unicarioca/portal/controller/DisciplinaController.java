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

        log.info("GET /disciplinas/list");
        return ResponseEntity.ok(disciplinaService.getAllDisciplinas());

    }

    @GetMapping("")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@RequestParam(required = false) String nome, @RequestParam(required = false) String codigo) {

        log.info("GET /disciplinas/nome'={}&codigo={}",nome,codigo);
        return ResponseEntity.ok(disciplinaService.getDisciplina(nome, codigo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponse> getDisciplina(@PathVariable("id") Long id) {

        log.info("GET /disciplinas/ id={}",id);
        return ResponseEntity.ok(disciplinaService.getDisciplina(id));

    }

    @PostMapping("")
    public ResponseEntity<DisciplinaResponse> saveDisciplina(@Validated @RequestBody DisciplinaRequest disciplinaRequest) {

        log.info("POST /disciplinas/ request={}",disciplinaRequest);
        return ResponseEntity.ok(disciplinaService.saveDisciplina(disciplinaRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable("id") Long id) {

        log.info("DELETE /disciplinas/ id={}",id);
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.ok().build();

    }
}
