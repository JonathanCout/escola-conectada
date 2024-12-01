package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Merenda;
import com.unicarioca.portal.service.MerendaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merendas")
@RequiredArgsConstructor
public class MerendaController {

    private final Logger log = LoggerFactory.getLogger(MerendaController.class);
    private MerendaService merendaService;

    @GetMapping("")
    public ResponseEntity<Merenda> getMerenda(@RequestParam String nome) {
        try{
            log.info("GET /merendas/name={}",nome);
            return ResponseEntity.ok(merendaService.getMerenda(nome));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Merenda>> getAllMerendas(){
        try{
            log.info("GET /eventos/list");
            return ResponseEntity.ok(merendaService.getAllMerendas());
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merenda> getMerendaById(@PathVariable("id") Long id) {
        try{
            log.info("GET /merendas/id={}",id);
            return ResponseEntity.ok(merendaService.getMerendaById(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Merenda> saveMerenda(@RequestBody Merenda merenda) {
        try{
            log.info("POST /merendas/request={}",merenda);
            return ResponseEntity.ok(merendaService.saveMerenda(merenda));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merenda> updateMerenda(@PathVariable("id") Long id, @RequestBody Merenda merenda) {
        try{
            log.info("PUT /merendas/id={},request={}",id, merenda);
            return ResponseEntity.ok(merendaService.updateMerenda(id, merenda));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerenda(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /merendas/id={}",id);
            merendaService.deleteMerenda(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
