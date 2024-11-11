package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;
import br.com.sicredi.votacao.application.exception.SessaoException;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.Pauta;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AbrirSessaoVotacaoUseCaseTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaRepository pautaRepository;
    private static final String PAUTA = "Assembleia 2024";

    @InjectMocks
    private AbrirSessaoVotacaoUseCase useCase;

    private SessaoRequest sessaoRequest;
    private Pauta pautaAberta;
    private Pauta pautaFechada;
    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    void setUp() {
        sessaoRequest = SessaoRequest.builder()
                .idPauta(1L)
                .fim(LocalDateTime.now().plusHours(1))
                .build();

        pautaAberta = Pauta.builder()
                .id(1L)
                .titulo(PAUTA)
                .status(Status.ABERTA)
                .build();

        pautaFechada = Pauta.builder()
                .id(1L)
                .titulo(PAUTA)
                .status(Status.FECHADA)
                .build();

        sessaoVotacao = SessaoVotacao.builder()
                .id(1L)
                .idPauta(1L)
                .status(Status.ABERTA)
                .inicio(LocalDateTime.now())
                .fim(LocalDateTime.now().plusHours(1))
                .build();
    }

    @Test
    void testExecuteWhenSessionOpenedThenReturnSessaoResponse() {
        given(pautaRepository.findById(sessaoRequest.getIdPauta())).willReturn(Optional.of(pautaAberta));
        given(sessaoVotacaoRepository.save(any(SessaoVotacao.class))).willReturn(sessaoVotacao);

        SessaoResponse result = useCase.execute(sessaoRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(sessaoVotacao.getId());
        assertThat(result.getIdPauta()).isEqualTo(sessaoVotacao.getIdPauta());
        assertThat(result.getStatus()).isEqualTo(sessaoVotacao.getStatus());
        assertThat(result.getInicio()).isEqualTo(sessaoVotacao.getInicio());
        assertThat(result.getFim()).isEqualTo(sessaoVotacao.getFim());
    }

    @Test
    void testExecuteWhenPautaNotFoundThenThrowSessaoException() {
        given(pautaRepository.findById(sessaoRequest.getIdPauta())).willReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(sessaoRequest))
                .isInstanceOf(SessaoException.class);
    }

    @Test
    void testExecuteWhenPautaNotInAbertaStatusThenThrowSessaoException() {
        given(pautaRepository.findById(sessaoRequest.getIdPauta())).willReturn(Optional.of(pautaFechada));

        assertThatThrownBy(() -> useCase.execute(sessaoRequest))
                .isInstanceOf(SessaoException.class);
    }
}
