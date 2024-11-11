package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.application.exception.VotoException;
import br.com.sicredi.votacao.domain.enums.Decisao;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.domain.model.Voto;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import br.com.sicredi.votacao.domain.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceberVotoUseCaseTest {

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @InjectMocks
    private ReceberVotoUseCase receberVotoUseCase;

    private Voto voto;
    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    public void setUp() {
        voto = Voto.builder()
                .cpf("12345678900")
                .idPauta(1L)
                .voto(Decisao.SIM)
                .build();

        sessaoVotacao = SessaoVotacao.builder()
                .id(1L)
                .idPauta(1L)
                .inicio(LocalDateTime.now().minusHours(1))
                .fim(LocalDateTime.now().plusHours(1))
                .build();
    }

    @Test
    public void testExecuteWhenVotingSessionClosedThenThrowIllegalStateException() {
        when(sessaoVotacaoRepository.findByIdPauta(voto.getIdPauta())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> receberVotoUseCase.execute(voto));
    }

    @Test
    public void testExecuteWhenVotingSessionOpenAndVoteValidThenReturnVotoDTO() {
        when(sessaoVotacaoRepository.findByIdPauta(voto.getIdPauta())).thenReturn(Optional.of(sessaoVotacao));
        when(votoRepository.existsByCpfAndIdPauta(voto.getCpf(), voto.getIdPauta())).thenReturn(false);
        when(votoRepository.save(voto)).thenReturn(voto);

        VotoDTO result = receberVotoUseCase.execute(voto);

        assertNotNull(result);
        assertEquals(voto.getCpf(), result.getCpf());
        assertEquals(voto.getIdPauta(), result.getIdPauta());
        assertEquals(voto.getVoto(), result.getVoto());
    }

    @Test
    public void testExecuteWhenVotingSessionOpenButVoteInvalidThenThrowVotoException() {
        when(sessaoVotacaoRepository.findByIdPauta(voto.getIdPauta())).thenReturn(Optional.of(sessaoVotacao));
        when(votoRepository.existsByCpfAndIdPauta(voto.getCpf(), voto.getIdPauta())).thenReturn(true);

        assertThrows(VotoException.class, () -> receberVotoUseCase.execute(voto));
    }

    @Test
    public void testValidaVotoPersisteNestaPautaWhenVoteValidThenReturnVoto() {
        when(votoRepository.existsByCpfAndIdPauta(voto.getCpf(), voto.getIdPauta())).thenReturn(false);
        when(votoRepository.save(voto)).thenReturn(voto);

        Voto result = receberVotoUseCase.validaVotoPersisteNestaPauta(voto).apply(sessaoVotacao);

        assertNotNull(result);
        assertEquals(voto.getCpf(), result.getCpf());
        assertEquals(voto.getIdPauta(), result.getIdPauta());
        assertEquals(voto.getVoto(), result.getVoto());
    }

    @Test
    public void testValidaVotoPersisteNestaPautaWhenVoteInvalidThenThrowVotoException() {
        when(votoRepository.existsByCpfAndIdPauta(voto.getCpf(), voto.getIdPauta())).thenReturn(true);

        assertThrows(VotoException.class, () -> receberVotoUseCase.validaVotoPersisteNestaPauta(voto).apply(sessaoVotacao));
    }
}
