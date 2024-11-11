package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.gateway.ValidadorCpfGateway;
import br.com.sicredi.votacao.infra.utils.CpfValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class VerificarCpfUseCase implements ValidadorCpfGateway {

    public Boolean podeVotar(final String cpf) {
        log.info("Validando CPF {}", cpf);
        return Optional.ofNullable(cpf)
                .stream().anyMatch(CpfValidator::validate);
    }

}