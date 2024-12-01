package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import com.unicarioca.portal.service.TurmaService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final Logger log = LoggerFactory.getLogger(TurmaController.class);
    private TurmaService turmaService;

    @GetMapping("")
    public ResponseEntity<TurmaResponse> getTurma(@RequestParam(required = false) String codigo, @RequestParam(required = false) String nome) {
        try{
            log.info("GET /turmas/codigo={},nome={}",codigo,nome);
            return ResponseEntity.ok(turmaService.getTurma(codigo,nome));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> getTurma(@PathVariable("id") Long id) {
        try{
            log.info("POST /turmas/id={}",id);
            return ResponseEntity.ok(turmaService.getTurma(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("")
    public ResponseEntity<TurmaResponse> saveTurma(@Validated @RequestBody TurmaRequest turmaRequest) {
        try{
            log.info("POST /turmas/request={}",turmaRequest);
            return ResponseEntity.ok(turmaService.saveTurma(turmaRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> updateTurma(@PathVariable("id") Long id, @Validated @RequestBody TurmaRequest turmaRequest) {
        try{
            log.info("PUT /turmas/id={},request={}",id, turmaRequest);
            return ResponseEntity.ok(turmaService.updateTurma(id, turmaRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /turmas/id={}",id);
            turmaService.deleteTurma(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


}
