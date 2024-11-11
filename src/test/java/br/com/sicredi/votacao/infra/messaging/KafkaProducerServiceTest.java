package br.com.sicredi.votacao.infra.messaging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Test
    public void testSendMessageWhenCalledThenMessageSentToCorrectTopic() {
        String topic = "test-topic";
        Object message = "test-message";

        kafkaProducerService.sendMessage(topic, message);

        verify(kafkaTemplate).send(topic, message);
    }
}
