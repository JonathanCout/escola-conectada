package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.MensagemRequest;
import com.unicarioca.portal.entity.Mensagem;
import com.unicarioca.portal.repository.MensagemRepository;
import com.unicarioca.portal.service.mapper.MensagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensagemCrudService {

    @Autowired
    private MensagemRepository mensagemRepository;

    public Mensagem getMensagemById(Long id){
        return mensagemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Mensagem saveMensagem(MensagemRequest mensagemRequest){
        return mensagemRepository.save(MensagemMapper.toEntity(mensagemRequest));
    }

    @Transactional
    public void deleteMensagem(Long id){
        mensagemRepository.deleteById(id);
    }
}
