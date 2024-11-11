package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;
import br.com.sicredi.votacao.application.exception.SessaoException;
import br.com.sicredi.votacao.application.gateway.SessaoVotacaoGateway;
import br.com.sicredi.votacao.application.mapper.SessaoMapper;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AbrirSessaoVotacaoUseCase implements SessaoVotacaoGateway {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaRepository pautaRepository;

    public SessaoResponse execute(final SessaoRequest sessaoRequest) {
        log.info("Abrindo sessão de votação para a pauta {}", sessaoRequest.getIdPauta());
        return pautaRepository.findById(sessaoRequest.getIdPauta())
                .filter(pauta -> Status.ABERTA.equals(pauta.getStatus()))
                .map(pautaAberta -> SessaoMapper.toEntity(sessaoRequest))
                .map(sessaoVotacaoRepository::save)
                .map(SessaoMapper::toResponse)
                .orElseThrow(SessaoException::new);
    }
}