package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.Pauta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PautaMapperTest {

    private static final String PAUTA="Assembleia 2024";

    @Test
    public void testToEntityWhenValidPautaRequestThenReturnPauta() {
        PautaRequest pautaRequest = PautaRequest.builder()
                .titulo(PAUTA)
                .build();

        Pauta pauta = PautaMapper.toEntity(pautaRequest);

        assertNotNull(pauta);
        assertEquals(pautaRequest.getTitulo(), pauta.getTitulo());
        assertEquals(Status.ABERTA, pauta.getStatus());
    }

    @Test
    public void testToDtoWhenValidPautaThenReturnPautaResponse() {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .titulo(PAUTA)
                .status(Status.ABERTA)
                .build();

        PautaResponse pautaResponse = PautaMapper.toDto(pauta);

        assertNotNull(pautaResponse);
        assertEquals(pauta.getId(), pautaResponse.getId());
        assertEquals(pauta.getTitulo(), pautaResponse.getTitulo());
        assertEquals(pauta.getStatus(), pautaResponse.getStatus());
    }
}
