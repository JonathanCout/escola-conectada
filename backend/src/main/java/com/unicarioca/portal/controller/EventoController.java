package com.unicarioca.portal.controller;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.service.EventoService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private EventoService eventoService;

    @GetMapping("")
    public ResponseEntity<Evento> getEvento(@RequestParam(required = false) String nome) {
        try{
            return ResponseEntity.ok(eventoService.getEvento(nome));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathParam("id") Long id) {
        try{
            return ResponseEntity.ok(eventoService.getEventoById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Evento> saveEvento(@RequestBody Evento evento) {
        try{
            return ResponseEntity.ok(eventoService.saveEvento(evento));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathParam("id") Long id, @RequestBody Evento evento) {
        try{
            return ResponseEntity.ok(eventoService.updateEvento(id, evento));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathParam("id") Long id) {
        try{
            eventoService.deleteEvento(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
