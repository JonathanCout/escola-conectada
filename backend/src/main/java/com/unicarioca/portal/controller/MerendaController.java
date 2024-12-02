package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Merenda;
import com.unicarioca.portal.service.MerendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merendas")
public class MerendaController {

    private final Logger log = LoggerFactory.getLogger(MerendaController.class);
    @Autowired
    private MerendaService merendaService;

    @GetMapping("")
    public ResponseEntity<Merenda> getMerenda(@RequestParam String nome) {

        log.info("GET /merendas/name={}",nome);
        return ResponseEntity.ok(merendaService.getMerenda(nome));

    }

    @GetMapping("/list")
    public ResponseEntity<List<Merenda>> getAllMerendas(){

        log.info("GET /eventos/list");
        return ResponseEntity.ok(merendaService.getAllMerendas());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Merenda> getMerendaById(@PathVariable("id") Long id) {

        log.info("GET /merendas/id={}",id);
        return ResponseEntity.ok(merendaService.getMerendaById(id));

    }

    @PostMapping("")
    public ResponseEntity<Merenda> saveMerenda(@RequestBody Merenda merenda) {

        log.info("POST /merendas/request={}",merenda);
        return ResponseEntity.ok(merendaService.saveMerenda(merenda));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Merenda> updateMerenda(@PathVariable("id") Long id, @RequestBody Merenda merenda) {

        log.info("PUT /merendas/id={},request={}",id, merenda);
        return ResponseEntity.ok(merendaService.updateMerenda(id, merenda));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerenda(@PathVariable("id") Long id) {

        log.info("DELETE /merendas/id={}",id);
        merendaService.deleteMerenda(id);
        return ResponseEntity.ok().build();

    }
}
