package com.unicarioca.portal.service;

import com.unicarioca.portal.entity.Merenda;
import com.unicarioca.portal.service.crud.MerendaCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerendaService {

    private MerendaCrudService merendaCrudService;

    public Merenda getMerenda(String nome) {
        return merendaCrudService.getMerenda(nome);
    }

    public Merenda getMerendaById(Long id) {
        return merendaCrudService.getMerendaById(id);
    }

    public Merenda saveMerenda(Merenda merenda) {
        return merendaCrudService.saveMerenda(merenda);
    }

    public Merenda updateMerenda(Long id, Merenda merenda) {
        Merenda merendaToUpdate = merendaCrudService.getMerendaById(id);
        merendaToUpdate.setNome(merenda.getNome());
        merendaToUpdate.setDescricao(merenda.getDescricao());
        merendaToUpdate.setTurno(merenda.getTurno());
        return merendaCrudService.updateMerenda(merendaToUpdate);
    }

    public void deleteMerenda(Long id) {
        merendaCrudService.deleteMerenda(id);
    }
}
