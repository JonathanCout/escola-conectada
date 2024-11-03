package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoCrudService {

    @Autowired
    private EventoRepository eventoRepository;

    public void deleteEvento(Long id){
        eventoRepository.deleteById(id);
    }

    public Evento getEventoById(Long id){
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento saveEvento(Evento evento){
        return eventoRepository.save(evento);
    }

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
}
