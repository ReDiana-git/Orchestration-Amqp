package nl.nl0e0.appointmentamqp.contract.producer;


import nl.nl0e0.appointmentamqp.AppointmentAmqpApplication;
import nl.nl0e0.appointmentamqp.contract.TestConfig;
import nl.nl0e0.appointmentamqp.service.AmqpReceiver;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestConfig.class, AppointmentAmqpApplication.class}, properties = "stubrunner.amqp.mockConnection=false")
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
    AmqpReceiver amqpReceiver;

    public void trigger() {
        String dateTimeString = "2024-07-05T14:45:33.851107";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        LocalDateTime dateTime = LocalDateTime.now();
        CreateAppointmentDTO dto = new CreateAppointmentDTO();
        dto.setAppointmentDate(dateTime);
        dto.setPetId(2);
        dto.setOwnerId(1);
        dto.setVetId(1);

        this.amqpReceiver.createAppointment(dto);

    }

}


