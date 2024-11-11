package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessaoMapperTest {

    private SessaoRequest sessaoRequest;
    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    public void setUp() {
        sessaoRequest = SessaoRequest.builder()
                .idPauta(1L)
                .fim(null)
                .build();

        sessaoVotacao = SessaoVotacao.builder()
                .id(1L)
                .idPauta(1L)
                .status(Status.ABERTA)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusMinutes(10))
                .build();
    }

    @Test
    public void testToEntityWhenFimIsNullThenFimIsSetToCurrentTimePlusOneMinute() {
        LocalDateTime before = LocalDateTime.now();

        SessaoVotacao result = SessaoMapper.toEntity(sessaoRequest);

        assertNotNull(result);
        assertEquals(sessaoRequest.getIdPauta(), result.getIdPauta());
        assertEquals(Status.ABERTA, result.getStatus());
        assertNotNull(result.getInicio());
        assertNotNull(result.getFim());
        assertTrue(result.getFim().isAfter(before.plusSeconds(59)) && result.getFim().isBefore(before.plusSeconds(61)));
    }

    @Test
    public void testToEntityWhenFimIsNotNullThenFimIsSetToRequestFim() {
        LocalDateTime fim = LocalDateTime.now().plusHours(1);
        sessaoRequest.setFim(fim);

        SessaoVotacao result = SessaoMapper.toEntity(sessaoRequest);

        assertNotNull(result);
        assertEquals(sessaoRequest.getIdPauta(), result.getIdPauta());
        assertEquals(Status.ABERTA, result.getStatus());
        assertNotNull(result.getInicio());
        assertEquals(fim, result.getFim());
    }

    @Test
    public void testToResponseWhenSessaoVotacaoIsGivenThenReturnSessaoResponse() {
        SessaoResponse result = SessaoMapper.toResponse(sessaoVotacao);

        assertNotNull(result);
        assertEquals(sessaoVotacao.getId(), result.getId());
        assertEquals(sessaoVotacao.getIdPauta(), result.getIdPauta());
        assertEquals(sessaoVotacao.getStatus(), result.getStatus());
        assertEquals(sessaoVotacao.getInicio(), result.getInicio());
        assertEquals(sessaoVotacao.getFim(), result.getFim());
    }
}
