package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import com.unicarioca.portal.service.TurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final Logger log = LoggerFactory.getLogger(TurmaController.class);
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/list")
    public ResponseEntity<List<TurmaResponse>> getAllTurmas(){

        log.info("GET /turmas/list");
        return ResponseEntity.ok(turmaService.getAllTurmas());

    }

    @GetMapping("")
    public ResponseEntity<TurmaResponse> getTurma(@RequestParam(required = false) String codigo, @RequestParam(required = false) String nome) {

        log.info("GET /turmas/codigo={},nome={}",codigo,nome);
        return ResponseEntity.ok(turmaService.getTurma(codigo,nome));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> getTurma(@PathVariable("id") Long id) {

        log.info("POST /turmas/id={}",id);
        return ResponseEntity.ok(turmaService.getTurma(id));

    }


    @PostMapping("")
    public ResponseEntity<TurmaResponse> saveTurma(@Validated @RequestBody TurmaRequest turmaRequest) {

        log.info("POST /turmas/request={}",turmaRequest);
        return ResponseEntity.ok(turmaService.saveTurma(turmaRequest));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> updateTurma(@PathVariable("id") Long id, @Validated @RequestBody TurmaRequest turmaRequest) {

        log.info("PUT /turmas/id={},request={}",id, turmaRequest);
        return ResponseEntity.ok(turmaService.updateTurma(id, turmaRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable("id") Long id) {

        log.info("DELETE /turmas/id={}",id);
        turmaService.deleteTurma(id);
        return ResponseEntity.ok().build();

    }

}
