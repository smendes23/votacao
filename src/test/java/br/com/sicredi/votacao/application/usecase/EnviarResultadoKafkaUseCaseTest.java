package br.com.sicredi.votacao.application.usecase;

import br.com.sicredi.votacao.infra.messaging.KafkaProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EnviarResultadoKafkaUseCaseTest {

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private EnviarResultadoKafkaUseCase enviarResultadoKafkaUseCase;

    @Test
    void testExecuteWhenCalledThenSendMessageWithCorrectParameters() {
        String topic = "test-topic";
        Object message = new Object();

        enviarResultadoKafkaUseCase.execute(topic, message);

        verify(kafkaProducerService).sendMessage(topic, message);
    }
}
