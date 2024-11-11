package br.com.sicredi.votacao.application.controller.pauta.dto.response;

import br.com.sicredi.votacao.domain.enums.Status;
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
public class PautaResponse {

    private Long id;

    private String titulo;

    private Status status;

}
