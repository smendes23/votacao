package br.com.sicredi.votacao.application.controller.resultado.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ResultadoResponse {
    @JsonProperty("votos_sim")
    private Long votosSim;
    @JsonProperty("votos_nao")
    private Long votosNao;
    @JsonProperty("total_votos")
    private Long totalVotos;
}
