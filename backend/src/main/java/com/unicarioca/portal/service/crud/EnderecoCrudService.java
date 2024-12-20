package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.EnderecoRequest;
import com.unicarioca.portal.entity.Endereco;
import com.unicarioca.portal.repository.EnderecoRepository;
import com.unicarioca.portal.service.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoCrudService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco getEnderecoById(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Endereco saveEndereco(EnderecoRequest enderecoRequest) {
        return enderecoRepository.save(EnderecoMapper.toEntity(enderecoRequest));
    }

    @Transactional
    public Endereco updateEndereco(Long id, EnderecoRequest enderecoRequest) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco == null) {
            return null;
        }

        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());

        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void deleteEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

}
