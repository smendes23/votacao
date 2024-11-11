package br.com.sicredi.votacao.application.gateway;

public interface KafkaProducerGateway {
    void execute(final String topic, final Object message);
}
