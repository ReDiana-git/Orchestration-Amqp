package nl.nl0e0.consultationamqp.contract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.noop.NoOpStubMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Configuration
public class TestConfig{
    private static final Logger log = LoggerFactory.getLogger(TestConfig.class);
    @Bean
    MessageVerifierSender<Message<?>> testMessageVerifier(RabbitTemplate rabbitTemplate) {
        return new MessageVerifierSender<>() {

            @Override
            public void send(Message<?> message, String destination, @Nullable YamlContract contract) {
                log.info("Sending a message to destination [{}]", destination);
                System.out.println("Sending a message" + message);
//				rabbitTemplate.convertAndSend("createAppointmentExchange", destination, message);
                rabbitTemplate.send(destination, toMessage(message));
            }

            @Override
            public <T> void send(T payload, Map<String, Object> headers, String destination, @Nullable YamlContract contract) {
                log.info("Sending a message to destination [{}]", destination);
                send(org.springframework.messaging.support.MessageBuilder.withPayload(payload).copyHeaders(headers).build(), destination, contract);
            }

            private org.springframework.amqp.core.Message toMessage(Message<?> msg) {
                Object payload = msg.getPayload();
                MessageHeaders headers = msg.getHeaders();
                Map<String, Object> newHeaders = headers != null ? new HashMap<>(headers) : new HashMap<>();
                MessageProperties messageProperties = new MessageProperties();
                newHeaders.forEach(messageProperties::setHeader);
                if (payload instanceof String) {
                    String json = (String) payload;
                    org.springframework.amqp.core.Message message = MessageBuilder.withBody(json.getBytes(StandardCharsets.UTF_8)).andProperties(messageProperties).build();
                    return message;
                } else {
                    throw new IllegalStateException("Payload is not a String");
                }
            }
        };

    }
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
class RabbitMessageVerifier implements MessageVerifierReceiver<Message> {
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


    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 1, TimeUnit.SECONDS, contract);
    }
}