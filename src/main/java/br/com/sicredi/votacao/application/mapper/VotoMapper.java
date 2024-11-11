package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.domain.model.Voto;

public class VotoMapper {

    public static Voto toEntity(VotoDTO dto) {
        return Voto.builder()
                .idPauta(dto.getIdPauta())
                .cpf(dto.getCpf())
                .voto(dto.getVoto())
                .build();
    }

    public static VotoDTO toDto(Voto entity) {
        return VotoDTO.builder()
                .idPauta(entity.getIdPauta())
                .cpf(entity.getCpf())
                .voto(entity.getVoto())
                .build();
    }
}