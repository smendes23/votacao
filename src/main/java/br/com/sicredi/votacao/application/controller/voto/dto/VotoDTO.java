package br.com.sicredi.votacao.application.controller.voto.dto;

import br.com.sicredi.votacao.domain.enums.Decisao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDTO implements Serializable {

    @JsonProperty("id_pauta")
    private Long idPauta;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("voto")
    private Decisao voto;
}
