package br.com.sicredi.votacao.infra.utils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class CpfValidator {

    public static Boolean validate(String cpf) {
        if (isInvalidFormat(cpf)) {
            return false;
        }

        try {
            int[] digits = toDigits.apply(cpf);
            int firstCheckDigit = calculateCheckDigit.apply(digits, 9);
            int secondCheckDigit = calculateCheckDigit.apply(digits, 10);

            return digits[9] == firstCheckDigit && digits[10] == secondCheckDigit;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isInvalidFormat(String cpf) {
        return cpf == null || cpf.length() != 11 || cpf.chars().distinct().count() == 1;
    }

    private static final Function<String, int[]> toDigits = cpf -> cpf.chars().map(dig -> dig - '0').toArray();

    private static final BiFunction<int[], Integer, Integer> calculateCheckDigit = (digits, length) -> {
        int sum = IntStream.range(0, length)
                .map(i -> digits[i] * (length + 1 - i))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    };
}