package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.infra.messaging.dto.MensagemResultado;
import br.com.sicredi.votacao.infra.utils.LocalDateTimeTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MensagemMapper {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();

    public static String toJson(final SessaoVotacao sessaoVotacao, final ResultadoResponse resultado) {
        return gson.toJson(MensagemResultado.builder()
                .sessaoVotacao(sessaoVotacao)
                .resultado(resultado)
                .build());
    }
}
