package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.ParenteRequest;
import com.unicarioca.portal.entity.Parente;
import com.unicarioca.portal.repository.ParenteRepository;
import com.unicarioca.portal.service.mapper.ParenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParenteCrudService {

    private ParenteRepository parenteRepository;

    public Parente getParenteByCpf(String cpf){
        return parenteRepository.findByCpf(cpf);
    }

    public Parente saveParente(ParenteRequest parenteRequest){
        Parente parente = getParenteByCpf(parenteRequest.getCpf());
        if (parente != null){
            return parente;
        }
        return parenteRepository.save(ParenteMapper.toEntity(parenteRequest));
    }
}
