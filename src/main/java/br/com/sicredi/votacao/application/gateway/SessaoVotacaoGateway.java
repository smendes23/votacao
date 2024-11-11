package br.com.sicredi.votacao.application.gateway;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;

public interface SessaoVotacaoGateway {
    SessaoResponse execute(final SessaoRequest sessaoRequest);
}
