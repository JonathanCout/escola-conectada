package com.unicarioca.portal.controller;

import com.unicarioca.portal.controller.dto.AlunoRequest;
import com.unicarioca.portal.controller.dto.AlunoResponse;
import com.unicarioca.portal.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final Logger log = LoggerFactory.getLogger(AlunoController.class);
    @Autowired
    private AlunoService alunoService;

    @GetMapping("")
    public ResponseEntity<AlunoResponse> getAluno(@RequestParam(required = false) String cpf, @RequestParam(required = false) String matricula, @RequestParam(required = false) String email) {
        try{
            log.info("GET /alunos?cpf={}&matricula={}", cpf, matricula);
            return ResponseEntity.ok(alunoService.getAluno(cpf, matricula, email));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getAlunoById(@PathVariable("id") Long id) {
        try{
            log.info("GET /alunos/id={}",id);
            return ResponseEntity.ok(alunoService.getAlunoById(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<AlunoResponse> saveAluno(@RequestBody AlunoRequest alunoRequest) {
        try{
            log.info("POST /alunos/request={}",alunoRequest);
            return ResponseEntity.ok(alunoService.saveAluno(alunoRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /alunos/id={}",id);
            alunoService.deleteAluno(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> updateAluno(@PathVariable("id") Long id, @RequestBody AlunoRequest alunoRequest) {
        try{
            log.info("PUT /alunos/id={}request={}",id, alunoRequest);
            return ResponseEntity.ok(alunoService.updateAluno(id, alunoRequest));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
