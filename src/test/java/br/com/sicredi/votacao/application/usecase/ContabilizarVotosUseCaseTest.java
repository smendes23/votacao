package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.domain.enums.Decisao;
import br.com.sicredi.votacao.domain.model.Voto;
import br.com.sicredi.votacao.domain.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContabilizarVotosUseCaseTest {

    @Mock
    private VotoRepository votoRepository;

    @InjectMocks
    private ContabilizarVotosUseCase contabilizarVotosUseCase;

    @Test
    void testExecuteWhenVotesExistThenReturnCorrectResult() {
        Long idPauta = 1L;
        List<Voto> votos = List.of(
                Voto.builder().cpf("123").idPauta(idPauta).voto(Decisao.SIM).build(),
                Voto.builder().cpf("456").idPauta(idPauta).voto(Decisao.NAO).build(),
                Voto.builder().cpf("789").idPauta(idPauta).voto(Decisao.SIM).build()
        );
        when(votoRepository.findAllByIdPauta(idPauta)).thenReturn(votos);

        ResultadoResponse resultado = contabilizarVotosUseCase.execute(idPauta);

        assertThat(resultado.getVotosSim()).isEqualTo(2);
        assertThat(resultado.getVotosNao()).isEqualTo(1);
        assertThat(resultado.getTotalVotos()).isEqualTo(3);
    }

    @Test
    void testExecuteWhenNoVotesThenReturnZeroResult() {
        Long idPauta = 1L;
        when(votoRepository.findAllByIdPauta(idPauta)).thenReturn(List.of());

        ResultadoResponse resultado = contabilizarVotosUseCase.execute(idPauta);

        assertThat(resultado.getVotosSim()).isEqualTo(0);
        assertThat(resultado.getVotosNao()).isEqualTo(0);
        assertThat(resultado.getTotalVotos()).isEqualTo(0);
    }
}
