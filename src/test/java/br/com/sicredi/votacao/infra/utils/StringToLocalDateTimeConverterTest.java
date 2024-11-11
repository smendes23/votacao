package br.com.sicredi.votacao.infra.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ExtendWith(MockitoExtension.class)
public class StringToLocalDateTimeConverterTest {

    private StringToLocalDateTimeConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new StringToLocalDateTimeConverter();
    }

    @Test
    public void testConvertWhenValidISODateTimeStringThenReturnLocalDateTime() {
        String validISODateTimeString = "2023-10-01T12:30:00";
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 10, 1, 12, 30);

        LocalDateTime result = converter.convert(validISODateTimeString);

        Assertions.assertThat(result).isEqualTo(expectedDateTime);
    }

    @Test
    public void testConvertWhenInvalidDateTimeStringThenThrowDateTimeParseException() {
        String invalidDateTimeString = "invalid-date-time";

        Assertions.assertThatThrownBy(() -> converter.convert(invalidDateTimeString))
                .isInstanceOf(DateTimeParseException.class);
    }
}
