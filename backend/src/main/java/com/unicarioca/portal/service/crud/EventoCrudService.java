package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoCrudService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento getEvento(String name){
        return eventoRepository.findByNome(name);
    }

    public List<Evento> getAllEventos(){
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id){
        return eventoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Evento saveEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento updateEvento(Long id, Evento evento){
        Evento eventoToUpdate = eventoRepository.findById(id).orElse(null);
        if (eventoToUpdate == null) {
            return null;
        }
        eventoToUpdate.setNome(evento.getNome());
        eventoToUpdate.setDescricao(evento.getDescricao());
        eventoToUpdate.setData(evento.getData());
        eventoToUpdate.setLocal(evento.getLocal());
        return eventoRepository.save(eventoToUpdate);
    }

    @Transactional
    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

}
