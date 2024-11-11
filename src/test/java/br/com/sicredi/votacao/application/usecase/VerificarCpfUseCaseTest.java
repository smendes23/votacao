package br.com.sicredi.votacao.application.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class VerificarCpfUseCaseTest {

    @InjectMocks
    private VerificarCpfUseCase verificarCpfUseCase;

    @Test
    public void testPodeVotarWhenCpfIsValidThenReturnTrue() {
        String validCpf = "12345678909";

        Boolean result = verificarCpfUseCase.podeVotar(validCpf);

        assertThat(result).isTrue();
    }

    @Test
    public void testPodeVotarWhenCpfIsInvalidThenReturnFalse() {
        String invalidCpf = "12345678900";

        Boolean result = verificarCpfUseCase.podeVotar(invalidCpf);

        assertThat(result).isFalse();
    }

    @Test
    public void testPodeVotarWhenCpfIsNullThenReturnFalse() {
        String nullCpf = null;

        Boolean result = verificarCpfUseCase.podeVotar(nullCpf);

        assertThat(result).isFalse();
    }
}
