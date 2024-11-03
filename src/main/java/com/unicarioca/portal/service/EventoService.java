package com.unicarioca.portal.service;

import com.unicarioca.portal.entity.Evento;
import com.unicarioca.portal.repository.EventoRepository;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private EventoRepository eventoRepository;
    public Evento getEvento(String nome) {
        return eventoRepository.findByNome(nome);
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento saveEvento(Evento eventoRequest) {
        return eventoRepository.save(eventoRequest);
    }

    public Evento updateEvento(Long id, Evento eventoNovo) {
        Evento evento = getEventoById(id);
        evento.setNome(eventoNovo.getNome() == null ? evento.getNome() : eventoNovo.getNome());
        evento.setDescricao(eventoNovo.getDescricao() == null ? evento.getDescricao() : eventoNovo.getDescricao());
        evento.setData(eventoNovo.getData() == null ? evento.getData() : eventoNovo.getData());
        evento.setLocal(eventoNovo.getLocal() == null ? evento.getLocal() : eventoNovo.getLocal());

        return eventoRepository.save(evento);
    }

    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
