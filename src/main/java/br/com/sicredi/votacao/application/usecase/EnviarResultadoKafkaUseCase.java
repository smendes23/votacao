package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.application.gateway.KafkaProducerGateway;
import br.com.sicredi.votacao.infra.messaging.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnviarResultadoKafkaUseCase implements KafkaProducerGateway {

    private final KafkaProducerService kafkaProducerService;

    public void execute(final String topic, final Object message) {
        log.info("Enviando mensagem para o tópico {}", topic);
        kafkaProducerService.sendMessage(topic, message);
    }
}
