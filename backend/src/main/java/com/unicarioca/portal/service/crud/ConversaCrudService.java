package com.unicarioca.portal.service.crud;

import com.unicarioca.portal.controller.dto.ConversaRequest;
import com.unicarioca.portal.controller.dto.MensagemRequest;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Conversa;
import com.unicarioca.portal.entity.Professor;
import com.unicarioca.portal.repository.ConversaRepository;
import com.unicarioca.portal.service.mapper.ConversaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConversaCrudService {

    @Autowired
    private ConversaRepository conversaRepository;
    @Autowired
    private AlunoCrudService alunoCrudService;
    @Autowired
    private ProfessorCrudService professorCrudService;
    @Autowired
    private MensagemCrudService mensagemCrudService;

    public Conversa getConversaById(Long conversaId) {
        return conversaRepository.findById(conversaId).orElse(null);
    }

    @Transactional
    public Conversa saveConversa(ConversaRequest conversaRequest) {
        Conversa conversa = ConversaMapper.toEntity(conversaRequest);
        Aluno aluno = alunoCrudService.getAlunoById(conversa.getAluno().getId());
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        conversa.setAluno(aluno);
        Professor professor = professorCrudService.getProfessorById(conversa.getProfessor().getId());
        if (professor == null) {
            throw new IllegalArgumentException("Professor não encontrado");
        }
        conversa.setProfessor(professor);
        return conversaRepository.save(conversa);
    }

    @Transactional
    public Conversa updateConversa(Long id, ConversaRequest conversaRequest) {
        Conversa conversa = conversaRepository.findById(id).orElse(null);
        if (conversa == null) {
            return null;
        }
        MensagemRequest mensagemRequest = conversaRequest.getMensagens();
        mensagemRequest.setConversaId(id);

        conversa.getMensagens().add(mensagemCrudService.saveMensagem(mensagemRequest));
        return conversaRepository.save(conversa);
    }

    @Transactional
    public void deleteConversa(Long id) {
        conversaRepository.deleteById(id);
    }

}
