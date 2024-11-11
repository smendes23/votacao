package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.application.gateway.ContabilizacaoGateway;
import br.com.sicredi.votacao.domain.enums.Decisao;
import br.com.sicredi.votacao.domain.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContabilizarVotosUseCase implements ContabilizacaoGateway {

    private final VotoRepository votoRepository;

    public ResultadoResponse execute(final Long idPauta) {
        log.info("Contabilizando votos da pauta {}", idPauta);
        return votoRepository.findAllByIdPauta(idPauta).stream()
                .collect(Collectors.teeing(
                        Collectors.filtering(voto -> Decisao.SIM.equals(voto.getVoto()), Collectors.counting()),
                        Collectors.counting(),
                        (votosSim, totalVotos) ->  ResultadoResponse.builder()
                                .votosSim(votosSim)
                                .votosNao(totalVotos - votosSim)
                                .totalVotos(totalVotos)
                                .build()));
    }
}
