package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final Logger log = LoggerFactory.getLogger(EventoController.class);
    private EventoService eventoService;

    @GetMapping("")
    public ResponseEntity<Evento> getEvento(@RequestParam(required = false) String nome) {
        try{
            log.info("GET /eventos/name={}",nome);
            return ResponseEntity.ok(eventoService.getEvento(nome));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable("id") Long id) {
        try{
            log.info("GET /eventos/id={}",id);
            return ResponseEntity.ok(eventoService.getEventoById(id));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Evento> saveEvento(@RequestBody Evento evento) {
        try{
            log.info("POST /eventos/request={}",evento);
            return ResponseEntity.ok(eventoService.saveEvento(evento));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable("id") Long id, @RequestBody Evento evento) {
        try{
            log.info("PUT /eventos/id={},request={}",id, evento);
            return ResponseEntity.ok(eventoService.updateEvento(id, evento));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable("id") Long id) {
        try{
            log.info("DELETE /eventos/id={}",id);
            eventoService.deleteEvento(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
