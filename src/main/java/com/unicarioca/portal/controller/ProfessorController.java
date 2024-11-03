package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.ProfessorRequest;
import com.unicarioca.portal.controller.dto.ProfessorResponse;
import com.unicarioca.portal.service.ProfessorService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private ProfessorService professorService;

    @GetMapping("")
    public ResponseEntity<ProfessorResponse> getProfessor(@RequestParam(required = false) String cpf, @RequestParam(required = false) String matricula) {
        try{
            return ResponseEntity.ok(professorService.getProfessor(cpf, matricula));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessor(@PathParam("id") Long id) {
        try{
            return ResponseEntity.ok(professorService.getProfessor(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ProfessorResponse> saveProfessor(@Validated @RequestBody ProfessorRequest professorRequest) {
        try{
            return ResponseEntity.ok(professorService.saveProfessor(professorRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathParam("id") Long id) {
        try{
            professorService.deleteProfessor(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
