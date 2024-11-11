package br.com.sicredi.votacao.infra.scheduler;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.application.gateway.ContabilizacaoGateway;
import br.com.sicredi.votacao.application.usecase.EnviarResultadoKafkaUseCase;
import br.com.sicredi.votacao.domain.enums.Status;
import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import br.com.sicredi.votacao.domain.repository.PautaRepository;
import br.com.sicredi.votacao.domain.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static br.com.sicredi.votacao.application.mapper.MensagemMapper.toJson;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessaoVotacaoScheduler {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final EnviarResultadoKafkaUseCase producer;
    private final PautaRepository pautaRepository;
    private final ContabilizacaoGateway contabilizacaoGateway;

    @Value("${kafka.topic}")
    private String topic;

    @Scheduled(fixedRate = 60000)
    public void fecharSessoesExpiradas() {
        sessaoVotacaoRepository.findAll().stream()
                .filter(Objects::nonNull)
                .filter(sessao -> sessao.getFim().isBefore(LocalDateTime.now()))
                .peek(sessao -> log.info("Fechando sessão de votação {}", sessao.getId()))
                .map(sessao -> {
                    sessao.setStatus(Status.FECHADA);
                    return sessaoVotacaoRepository.save(sessao);
                })
                .forEach(novaSessao -> {
                    atualizaStatusPauta(novaSessao.getIdPauta());
                    enviarMensagem(novaSessao);
                });
    }

    void enviarMensagem(final SessaoVotacao novaSessao) {
        log.info("Enviando resultado da votação da pauta {}", novaSessao.getIdPauta());
        ResultadoResponse resultadoResponse = contabilizacaoGateway.execute(novaSessao.getIdPauta());
        producer.execute(topic, toJson(novaSessao, resultadoResponse));
    }

    void atualizaStatusPauta(final Long idPauta) {
        pautaRepository.findById(idPauta).ifPresent(pauta -> {
            log.info("Atualizando status da pauta {}", pauta.getId());
            pauta.setStatus(Status.FECHADA);
            pautaRepository.save(pauta);
        });
    }
}
