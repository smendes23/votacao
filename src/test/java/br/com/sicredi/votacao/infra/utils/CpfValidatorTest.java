package br.com.sicredi.votacao.infra.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CpfValidatorTest {

    @Test
    public void testValidateWhenValidCpfThenReturnTrue() {
        String validCpf = "12345678909";

        boolean result = CpfValidator.validate(validCpf);

        assertTrue(result);
    }

    @Test
    public void testValidateWhenInvalidCpfThenReturnFalse() {
        String invalidCpf = "12345678900";

        boolean result = CpfValidator.validate(invalidCpf);

        assertFalse(result);
    }

    @Test
    public void testValidateWhenNullCpfThenReturnFalse() {
        String nullCpf = null;

        boolean result = CpfValidator.validate(nullCpf);

        assertFalse(result);
    }

    @Test
    public void testValidateWhenInvalidLengthCpfThenReturnFalse() {
        String shortCpf = "123456789";
        String longCpf = "123456789012";

        boolean shortResult = CpfValidator.validate(shortCpf);
        boolean longResult = CpfValidator.validate(longCpf);

        assertFalse(shortResult);
        assertFalse(longResult);
    }

    @Test
    public void testValidateWhenIdenticalDigitsCpfThenReturnFalse() {
        String identicalDigitsCpf = "11111111111";

        boolean result = CpfValidator.validate(identicalDigitsCpf);

        assertFalse(result);
    }
}
