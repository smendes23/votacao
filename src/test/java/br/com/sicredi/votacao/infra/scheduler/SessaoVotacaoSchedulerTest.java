package br.com.sicredi.votacao.infra.scheduler;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.application.gateway.ContabilizacaoGateway;
import br.com.sicredi.votacao.application.usecase.EnviarResultadoKafkaUseCase;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.Pauta;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessaoVotacaoSchedulerTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private EnviarResultadoKafkaUseCase producer;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private ContabilizacaoGateway contabilizacaoGateway;

    @InjectMocks
    private SessaoVotacaoScheduler sessaoVotacaoScheduler;

    private static final String TOPIC = "test-topic";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sessaoVotacaoScheduler = new SessaoVotacaoScheduler(sessaoVotacaoRepository, producer, pautaRepository, contabilizacaoGateway);
        ReflectionTestUtils.setField(sessaoVotacaoScheduler, "topic", TOPIC);
    }

    @Test
    public void testEnviarMensagemWhenCalledThenSendCorrectMessage() {
        SessaoVotacao sessao = SessaoVotacao.builder()
                .id(1L)
                .idPauta(1L)
                .status(Status.FECHADA)
                .fim(LocalDateTime.of(2024,11,11,7,10,10).plusMinutes(1))
                .inicio(LocalDateTime.of(2024,11,11,7,10,10))
                .build();
        String mensagem = "{\"resultado\":{\"votosSim\":10,\"votosNao\":5,\"totalVotos\":15},\"sessaoVotacao\":{\"id\":1,\"idPauta\":1,\"status\":\"FECHADA\",\"inicio\":\"2024-11-11T07:10:10\",\"fim\":\"2024-11-11T07:11:10\"}}";

        ResultadoResponse resultadoResponse = new ResultadoResponse(10L, 5L, 15L);
        when(contabilizacaoGateway.execute(1L)).thenReturn(resultadoResponse);

        sessaoVotacaoScheduler.enviarMensagem(sessao);

        verify(contabilizacaoGateway).execute(sessao.getIdPauta());
        verify(producer).execute(TOPIC, mensagem);
    }

    @Test
    public void testAtualizaStatusPautaWhenCalledThenUpdateStatusOfCorrectPauta() {
        Pauta pauta = Pauta.builder()
                .id(1L)
                .titulo("Test Pauta")
                .status(Status.ABERTA)
                .build();

        when(pautaRepository.findById(anyLong())).thenReturn(Optional.of(pauta));

        sessaoVotacaoScheduler.atualizaStatusPauta(1L);

        verify(pautaRepository).findById(1L);
        verify(pautaRepository).save(pauta);
        assertThat(pauta.getStatus()).isEqualTo(Status.FECHADA);
    }
}
