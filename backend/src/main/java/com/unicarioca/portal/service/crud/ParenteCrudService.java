package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.ParenteRequest;
import com.unicarioca.portal.entity.Parente;
import com.unicarioca.portal.repository.ParenteRepository;
import com.unicarioca.portal.service.mapper.ParenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParenteCrudService {

    private final Logger log = LoggerFactory.getLogger(ParenteCrudService.class);
    @Autowired
    private ParenteRepository parenteRepository;

    public Parente getParenteByCpf(String cpf){
        return parenteRepository.findByCpf(cpf);
    }

    @Transactional
    public Parente saveParente(ParenteRequest parenteRequest){
        log.info("Salvando parente={}", parenteRequest);
        Parente parente = getParenteByCpf(parenteRequest.getCpf());
        if (parente != null){
            return parente;
        }
        return parenteRepository.save(ParenteMapper.toEntity(parenteRequest));
    }
}
