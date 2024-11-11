package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.Pauta;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaMapper {

    public static Pauta toEntity(PautaRequest dto) {
        return Pauta.builder()
                .titulo(dto.getTitulo())
                .status(Status.ABERTA)
                .build();
    }

    public static PautaResponse toDto(Pauta entity) {
        return PautaResponse.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .status(entity.getStatus())
                .build();
    }
}
