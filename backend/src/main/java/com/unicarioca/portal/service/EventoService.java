package com.unicarioca.portal.service;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.repository.EventoRepository;
import com.unicarioca.portal.service.crud.EventoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoCrudService  eventoCrudService;

    public List<Evento> getAllEventos(){
        return eventoCrudService.getAllEventos();
    }

    public Evento getEvento(String nome) {
        return eventoCrudService.getEvento(nome);
    }

    public Evento getEventoById(Long id) {
        return eventoCrudService.getEventoById(id);
    }

    public Evento saveEvento(Evento eventoRequest) {
        return eventoCrudService.saveEvento(eventoRequest);
    }

    public Evento updateEvento(Long id, Evento eventoNovo) {
        return eventoCrudService.updateEvento(id, eventoNovo);
    }

    public void deleteEvento(Long id) {
        eventoCrudService.deleteEvento(id);
    }
}
