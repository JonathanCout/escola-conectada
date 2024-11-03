package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.TurmaRequest;
import com.unicarioca.portal.controller.dto.TurmaResponse;
import com.unicarioca.portal.service.TurmaService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private TurmaService turmaService;

    @GetMapping("")
    public ResponseEntity<TurmaResponse> getTurma(@RequestParam(required = false) String codigo, @RequestParam(required = false) String nome) {
        try{
            return ResponseEntity.ok(turmaService.getTurma(codigo,nome));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> getTurma(@PathParam("id") Long id) {
        try{
            return ResponseEntity.ok(turmaService.getTurma(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<TurmaResponse> saveTurma(@Validated @RequestBody TurmaRequest turmaRequest) {
        try{
            return ResponseEntity.ok(turmaService.saveTurma(turmaRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathParam("id") Long id) {
        try{
            turmaService.deleteTurma(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> updateTurma(@PathParam("id") Long id, @Validated @RequestBody TurmaRequest turmaRequest) {
        try{
            return ResponseEntity.ok(turmaService.updateTurma(id, turmaRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
