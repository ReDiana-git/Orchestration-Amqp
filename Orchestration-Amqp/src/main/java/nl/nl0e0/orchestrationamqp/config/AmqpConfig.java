package nl.nl0e0.orchestrationamqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    public Queue createConsultationQueue() {
        return new Queue("createConsultationQueue", false);
    }

    @Bean
    public Exchange createConsultationExchange()
    {
        return new DirectExchange("createConsultationExchange");
    }

    @Bean
    public Binding bindingCreateConsultation(Queue createConsultationQueue, Exchange createConsultationExchange)
    {
        return BindingBuilder.bind(createConsultationQueue)
                .to(createConsultationExchange)
                .with("createConsultation")
                .noargs();
    }
    @Bean
    public Queue createPaymentQueue() {
        return new Queue("createPaymentQueue", false);
    }

    @Bean
    public Exchange createPaymentExchange()
    {
        return new DirectExchange("createPaymentExchange");
    }

    @Bean
    public Binding bindingCreatePayment(Queue createPaymentQueue, Exchange createPaymentExchange)
    {
        return BindingBuilder.bind(createPaymentQueue)
                .to(createPaymentExchange)
                .with("createPayment")
                .noargs();
    }
    @Bean
    public Queue createMedicineQueue() {
        return new Queue("createMedicineQueue", false);
    }

    @Bean
    public Exchange createMedicineExchange()
    {
        return new DirectExchange("createMedicineExchange");
    }

    @Bean
    public Binding bindingCreateMedicine(Queue createMedicineQueue, Exchange createMedicineExchange)
    {
        return BindingBuilder.bind(createMedicineQueue)
                .to(createMedicineExchange)
                .with("createMedicine")
                .noargs();
    }

    @Bean
    public Queue createAppointmentQueue() {
        return new Queue("createAppointmentQueue", false);
    }

    @Bean
    public Exchange createAppointmentExchange()
    {
        return new DirectExchange("createAppointmentExchange");
    }

    @Bean
    public Binding bindingCreateAppointmentRecord(Queue createAppointmentQueue, Exchange createAppointmentExchange)
    {
        return BindingBuilder.bind(createAppointmentQueue)
                .to(createAppointmentExchange)
                .with("createAppointment")
                .noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}