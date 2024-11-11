package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MensagemMapperTest {

    @Test
    public void testToJsonWhenValidInputThenReturnExpectedJson() {
        SessaoVotacao sessaoVotacao = createSessaoVotacao();
        ResultadoResponse resultadoResponse = createResultadoResponse();

        String jsonResult = MensagemMapper.toJson(sessaoVotacao, resultadoResponse);

        String expectedJson = "{\"resultado\":{\"votosSim\":10,\"votosNao\":5,\"totalVotos\":15},\"sessaoVotacao\":{\"id\":1,\"idPauta\":2,\"status\":\"ABERTA\",\"inicio\":\"2023-10-01T10:00:00\",\"fim\":\"2023-10-01T12:00:00\"}}";
        assertThat(jsonResult).isEqualTo(expectedJson);
    }

    @Test
    public void testToJsonWhenNullInputThenReturnJsonWithNullValues() {
        String jsonResult = MensagemMapper.toJson(null, null);

        String expectedJson = "{}";
        assertThat(jsonResult).isEqualTo(expectedJson);
    }

    private SessaoVotacao createSessaoVotacao() {
        return SessaoVotacao.builder()
                .id(1L)
                .idPauta(2L)
                .status(Status.ABERTA)
                .inicio(LocalDateTime.of(2023, 10, 1, 10, 0))
                .fim(LocalDateTime.of(2023, 10, 1, 12, 0))
                .build();
    }

    private ResultadoResponse createResultadoResponse() {
        return ResultadoResponse.builder()
                .votosSim(10L)
                .votosNao(5L)
                .totalVotos(15L)
                .build();
    }
}
