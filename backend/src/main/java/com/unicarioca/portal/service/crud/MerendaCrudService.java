package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.entity.Merenda;
import com.unicarioca.portal.repository.MerendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MerendaCrudService {

    @Autowired
    private MerendaRepository merendaRepository;

    public Merenda getMerendaById(Long id) {
        return merendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Merenda não encontrada"));
    }

    public Merenda getMerenda(String nome) {
        return merendaRepository.findByNome(nome);
    }

    public List<Merenda> getAllMerendas(){
        return merendaRepository.findAll();
    }

    @Transactional
    public Merenda saveMerenda(Merenda merenda) {
        return merendaRepository.save(merenda);
    }

    @Transactional
    public void deleteMerenda(Long id) {
        merendaRepository.deleteById(id);
    }

    @Transactional
    public Merenda updateMerenda(Merenda merenda) {
        return merendaRepository.save(merenda);
    }
}
