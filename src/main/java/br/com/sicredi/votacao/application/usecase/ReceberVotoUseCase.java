package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.application.exception.VotoException;
import br.com.sicredi.votacao.application.gateway.VotoGateway;
import br.com.sicredi.votacao.application.mapper.VotoMapper;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.domain.model.Voto;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import br.com.sicredi.votacao.domain.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReceberVotoUseCase implements VotoGateway {

    private final VotoRepository votoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public VotoDTO execute(final Voto voto) {
        return sessaoVotacaoRepository.findByIdPauta(voto.getIdPauta())
                .filter(votacao -> votacao.getFim().isAfter(votacao.getInicio()))
                .map(validaVotoPersisteNestaPauta(voto))
                .map(VotoMapper::toDto)
                .orElseThrow(() -> new IllegalStateException("Sessão de votação está encerrada"));
    }

    public Function<SessaoVotacao, Voto> validaVotoPersisteNestaPauta(final Voto voto) {
        return response -> {
            log.info("Validando voto do cooperado {}", voto.getCpf());
            if (votoRepository.existsByCpfAndIdPauta(voto.getCpf(), voto.getIdPauta())) {
                throw new VotoException("Já existe um voto do cooperado: "+voto.getCpf());
            }
            return votoRepository.save(voto);
        };
    }
}
