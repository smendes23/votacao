package br.com.sicredi.votacao.application.gateway;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;

public interface ContabilizacaoGateway {
    ResultadoResponse execute(final Long idPauta);
}
