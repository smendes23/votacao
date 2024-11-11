package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SessaoMapper {

    public static SessaoVotacao toEntity(final SessaoRequest request) {
        return SessaoVotacao.builder()
                .idPauta(request.getIdPauta())
                .status(Status.ABERTA)
                .inicio(LocalDateTime.now())
                .fim(request.getFim() != null ? request.getFim() : LocalDateTime.now().plusMinutes(1))
                .build();
    }

    public static SessaoResponse toResponse(final SessaoVotacao sessaoVotacao) {
        return SessaoResponse.builder()
                .id(sessaoVotacao.getId())
                .idPauta(sessaoVotacao.getIdPauta())
                .status(sessaoVotacao.getStatus())
                .inicio(sessaoVotacao.getInicio())
                .fim(sessaoVotacao.getFim())
                .build();
    }
}
