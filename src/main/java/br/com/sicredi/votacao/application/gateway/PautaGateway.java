package br.com.sicredi.votacao.application.gateway;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;

public interface PautaGateway {
    PautaResponse execute(final PautaRequest pauta);
}
