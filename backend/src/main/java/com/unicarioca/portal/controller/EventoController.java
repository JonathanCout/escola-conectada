package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.service.EventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final Logger log = LoggerFactory.getLogger(EventoController.class);
    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public ResponseEntity<Evento> getEvento(@RequestParam(required = false) String nome) {

        log.info("GET /eventos/name={}",nome);
        return ResponseEntity.ok(eventoService.getEvento(nome));

    }

    @GetMapping("/list")
    public ResponseEntity<List<Evento>> getAllEventos(){

        log.info("GET /eventos/list");
        return ResponseEntity.ok(eventoService.getAllEventos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable("id") Long id) {

        log.info("GET /eventos/id={}",id);
        return ResponseEntity.ok(eventoService.getEventoById(id));

    }

    @PostMapping("")
    public ResponseEntity<Evento> saveEvento(@RequestBody Evento evento) {

        log.info("POST /eventos/request={}",evento);
        return ResponseEntity.ok(eventoService.saveEvento(evento));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable("id") Long id, @RequestBody Evento evento) {

        log.info("PUT /eventos/id={},request={}",id, evento);
        return ResponseEntity.ok(eventoService.updateEvento(id, evento));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable("id") Long id) {

        log.info("DELETE /eventos/id={}",id);
        eventoService.deleteEvento(id);
        return ResponseEntity.ok().build();

    }
}
