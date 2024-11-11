package br.com.sicredi.votacao.application.gateway;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.domain.model.Voto;

public interface VotoGateway {
    VotoDTO execute(final Voto voto);
}
