package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;
import br.com.sicredi.votacao.application.exception.PautaException;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.Pauta;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CadastrarPautaUseCaseTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private CadastrarPautaUseCase cadastrarPautaUseCase;

    private static final String PAUTA = "Assembleia 2024";

    private PautaRequest pautaRequest;

    @BeforeEach
    public void setUp() {
        pautaRequest = PautaRequest.builder()
                .titulo(PAUTA)
                .build();
    }

    @Test
    public void testExecuteWhenPautaIsNewThenRegisterPauta() {
        when(pautaRepository.existsByTitulo(pautaRequest.getTitulo())).thenReturn(false);
        Pauta pauta = Pauta.builder()
                .id(1L)
                .titulo(pautaRequest.getTitulo())
                .status(Status.ABERTA)
                .build();
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        PautaResponse pautaResponse = cadastrarPautaUseCase.execute(pautaRequest);

        assertThat(pautaResponse).isNotNull();
        assertThat(pautaResponse.getTitulo()).isEqualTo(pautaRequest.getTitulo());
        assertThat(pautaResponse.getStatus()).isEqualTo(Status.ABERTA);
        assertThat(pautaResponse.getId()).isEqualTo(1L);
    }

    @Test
    public void testExecuteWhenPautaAlreadyRegisteredThenThrowException() {
        when(pautaRepository.existsByTitulo(pautaRequest.getTitulo())).thenReturn(true);

        PautaException exception = assertThrows(PautaException.class, () -> {
            cadastrarPautaUseCase.execute(pautaRequest);
        });

        assertThat(exception.getMessage()).isEqualTo("Pauta já cadastrada");
    }
}
