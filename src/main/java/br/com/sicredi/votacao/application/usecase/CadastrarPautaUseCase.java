package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;
import br.com.sicredi.votacao.application.exception.PautaException;
import br.com.sicredi.votacao.application.gateway.PautaGateway;
import br.com.sicredi.votacao.application.mapper.PautaMapper;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Optional.of;

@Slf4j
@RequiredArgsConstructor
@Service
public class CadastrarPautaUseCase implements PautaGateway {

    private final PautaRepository pautaRepository;

    public PautaResponse execute(final PautaRequest pauta) {
        log.info("Cadastrando pauta {}", pauta.getTitulo());
        return of(pauta)
                .filter(p -> !pautaRepository.existsByTitulo(p.getTitulo()))
                .map(PautaMapper::toEntity)
                .map(pautaRepository::save)
                .map(PautaMapper::toDto)
                .orElseThrow(() -> new PautaException("Pauta já cadastrada"));
    }
}
