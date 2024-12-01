package com.unicarioca.portal.service.mapper;

import com.unicarioca.portal.controller.dto.ConversaRequest;
import com.unicarioca.portal.entity.Aluno;
import com.unicarioca.portal.entity.Conversa;
import com.unicarioca.portal.entity.Professor;

public class ConversaMapper {

    public static Conversa toEntity(ConversaRequest conversaRequest) {
        Conversa conversa = new Conversa();
        conversa.setAluno(new Aluno(){{setId(conversaRequest.getAlunoId());}});
        conversa.setProfessor(new Professor(){{setId(conversaRequest.getProfessorId());}});
        conversa.getMensagens().add(MensagemMapper.toEntity(conversaRequest.getMensagens()));
        return conversa;
    }


}
