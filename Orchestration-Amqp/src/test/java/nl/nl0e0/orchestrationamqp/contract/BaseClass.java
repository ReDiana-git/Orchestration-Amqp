package nl.nl0e0.orchestrationamqp.contract;

import nl.nl0e0.orchestrationamqp.OrchestrationAmqpApplication;
import nl.nl0e0.orchestrationamqp.controller.OrchestrationController;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.noop.NoOpStubMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, OrchestrationAmqpApplication.class}, properties = "stubrunner.amqp.mockConnection=false")
@Testcontainers
@AutoConfigureMessageVerifier
public class BaseClass {
    @Container
    static RabbitMQContainer rabbit = new RabbitMQContainer();

    @DynamicPropertySource
    static void rabbitProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
    }

    @Autowired
    OrchestrationController controller;

    public void trigger() {
        CreateAppointmentDTO dto = new CreateAppointmentDTO();
        dto.setAppointmentDate(LocalDateTime.now());
        dto.setPetId(2);
        dto.setOwnerId(1);
        dto.setVetId(1);
        this.controller.createAppointment(dto);

    }

}

@Configuration
class TestConfig{
    @Bean
    RabbitMessageVerifier rabbitTemplateMessageVerifier() {
        return new RabbitMessageVerifier();
    }
    @Bean
    ContractVerifierMessaging<Message> rabbitContractVerifierMessaging(RabbitMessageVerifier messageVerifier) {
        return new ContractVerifierMessaging<>(new NoOpStubMessages<>(), messageVerifier) {

            @Override
            protected ContractVerifierMessage convert(Message message) {
                if (message == null) {
                    return null;
                }
                return new ContractVerifierMessage(message.getPayload(), message.getHeaders());
            }

        };
    }


}
class RabbitMessageVerifier implements MessageVerifierReceiver<Message>{
    private static final Logger log = LoggerFactory.getLogger(RabbitMessageVerifier.class);

    private final LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, @Nullable YamlContract contract) {
        try {
            return queue.poll(timeout, timeUnit);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @RabbitListener(queues = "createAppointment")
    public void listen(Message message) {
        log.info("Got a message! [{}]", message);
        queue.add(message);
    }

    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 1, TimeUnit.SECONDS, contract);
    }
}
