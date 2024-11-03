package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Merenda;
import com.unicarioca.portal.service.MerendaService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merendas")
@RequiredArgsConstructor
public class MerendaController {

    private MerendaService merendaService;

    @GetMapping("")
    public ResponseEntity<Merenda> getMerenda(@RequestParam(required = false) String nome) {
        try{
            return ResponseEntity.ok(merendaService.getMerenda(nome));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merenda> getMerendaById(@PathParam("id") Long id) {
        try{
            return ResponseEntity.ok(merendaService.getMerendaById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Merenda> saveMerenda(@RequestBody Merenda merenda) {
        try{
            return ResponseEntity.ok(merendaService.saveMerenda(merenda));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merenda> updateMerenda(@PathParam("id") Long id, @RequestBody Merenda merenda) {
        try{
            return ResponseEntity.ok(merendaService.updateMerenda(id, merenda));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerenda(@PathParam("id") Long id) {
        try{
            merendaService.deleteMerenda(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
