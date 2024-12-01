package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.ParenteRequest;
import com.unicarioca.portal.controller.dto.ParenteResponse;
import com.unicarioca.portal.entity.Parente;

public class ParenteMapper {

    public static ParenteResponse toDto(Parente parente){
        ParenteResponse parenteResponse = new ParenteResponse();
        parenteResponse.setId(parente.getId());
        parenteResponse.setNome(parente.getNome());
        parenteResponse.setEmail(parente.getEmail());
        parenteResponse.setCpf(parente.getCpf());
        parenteResponse.setTelefone(parente.getTelefone());
        return parenteResponse;
    }
    public static Parente toEntity(ParenteRequest parenteRequest){

        Parente parente = new Parente();
        parente.setNome(parenteRequest.getNome());
        parente.setEmail(parenteRequest.getEmail());
        parente.setCpf(parenteRequest.getCpf());
        parente.setTelefone(parenteRequest.getTelefone());
        return parente;

    }
}
