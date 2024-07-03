package nl.nl0e0.appointmentamqp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    private final ObjectMapper objectMapper;

    public AmqpConfig(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Queue deleteAppointmentQueue() {
        return new Queue("deleteAppointmentQueue", false);
    }

    @Bean
    public Exchange deleteAppointmentExchange()
    {
        return new DirectExchange("deleteAppointmentExchange");
    }

    @Bean
    public Binding bindingDeleteAppointmentRecord(Queue deleteAppointmentQueue, Exchange deleteAppointmentExchange)
    {
        return BindingBuilder.bind(deleteAppointmentQueue)
                .to(deleteAppointmentExchange)
                .with("deleteAppointment")
                .noargs();
    }
    @Bean
    public Queue getIdByOwnerQueue() {
        return new Queue("getIdByOwnerQueue", false);
    }

    @Bean
    public Exchange getIdByOwnerExchange()
    {
        return new DirectExchange("getIdByOwnerExchange");
    }

    @Bean
    public Binding bindingGetIdByOwnerQueue(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("getIdByOwner")
                .noargs();
    }
    @Bean
    public Queue returnMedicalRecordQueue() {
        return new Queue("returnMedicalRecordQueue", false);
    }

    @Bean
    public Exchange returnMedicalRecordExchange()
    {
        return new DirectExchange("returnMedicalRecordExchange");
    }

    @Bean
    public Binding bindingReturnMedicalRecord(Queue returnMedicalRecordQueue, Exchange returnMedicalRecordExchange)
    {
        return BindingBuilder.bind(returnMedicalRecordQueue)
                .to(returnMedicalRecordExchange)
                .with("returnMedicalRecord")
                .noargs();
    }
    @Bean
    public Queue returnMedicalRecordsQueue() {
        return new Queue("returnMedicalRecordsQueue", false);
    }

    @Bean
    public Exchange returnMedicalRecordsExchange()
    {
        return new DirectExchange("returnMedicalRecordsExchange");
    }

    @Bean
    public Binding bindingReturnMedicalRecords(Queue returnMedicalRecordsQueue, Exchange returnMedicalRecordsExchange)
    {
        return BindingBuilder.bind(returnMedicalRecordsQueue)
                .to(returnMedicalRecordsExchange)
                .with("returnMedicalRecords")
                .noargs();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }
    @Bean
    public Queue createAppointmentQueue() {
        return new Queue("createAppointment", false);
    }

    @Bean
    public Exchange createAppointmentExchange()
    {
        return new TopicExchange("createAppointment");
    }

    @Bean
    public Binding bindingCreateAppointmentRecord(Queue createAppointmentQueue, Exchange createAppointmentExchange)
    {
        return BindingBuilder.bind(createAppointmentQueue)
                .to(createAppointmentExchange)
                .with("createAppointment")
                .noargs();
    }
}
