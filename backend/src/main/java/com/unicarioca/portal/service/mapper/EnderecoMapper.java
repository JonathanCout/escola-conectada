package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.EnderecoRequest;
import com.unicarioca.portal.controller.dto.EnderecoResponse;
import com.unicarioca.portal.entity.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequest enderecoRequest) {
        if ( enderecoRequest == null ) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setCep( enderecoRequest.getCep() );
        endereco.setLogradouro( enderecoRequest.getLogradouro() );
        endereco.setNumero( enderecoRequest.getNumero() );
        endereco.setComplemento( enderecoRequest.getComplemento() );
        endereco.setBairro( enderecoRequest.getBairro() );
        endereco.setCidade( enderecoRequest.getCidade() );
        endereco.setEstado( enderecoRequest.getEstado() );

        return endereco;
    }

    public static EnderecoResponse toDto(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(endereco.getId());
        enderecoResponse.setCep( endereco.getCep() );
        enderecoResponse.setLogradouro( endereco.getLogradouro() );
        enderecoResponse.setNumero( endereco.getNumero() );
        enderecoResponse.setComplemento( endereco.getComplemento() );
        enderecoResponse.setBairro( endereco.getBairro() );
        enderecoResponse.setCidade( endereco.getCidade() );
        enderecoResponse.setEstado( endereco.getEstado() );

        return enderecoResponse;
    }
}
