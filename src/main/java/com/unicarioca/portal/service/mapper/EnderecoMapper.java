package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.EnderecoRequest;
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

    public static EnderecoRequest toDto(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoRequest enderecoRequest = new EnderecoRequest();

        enderecoRequest.setCep( endereco.getCep() );
        enderecoRequest.setLogradouro( endereco.getLogradouro() );
        enderecoRequest.setNumero( endereco.getNumero() );
        enderecoRequest.setComplemento( endereco.getComplemento() );
        enderecoRequest.setBairro( endereco.getBairro() );
        enderecoRequest.setCidade( endereco.getCidade() );
        enderecoRequest.setEstado( endereco.getEstado() );

        return enderecoRequest;
    }
}
