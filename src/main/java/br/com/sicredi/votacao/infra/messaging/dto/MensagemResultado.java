package br.com.sicredi.votacao.infra.messaging.dto;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensagemResultado {
    private ResultadoResponse resultado;
    private SessaoVotacao sessaoVotacao;
}
