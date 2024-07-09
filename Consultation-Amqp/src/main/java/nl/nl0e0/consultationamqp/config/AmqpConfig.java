package nl.nl0e0.consultationamqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
    @Bean
    public Queue createConsultationQueue() {
        return new Queue("createConsultation", false);
    }

    @Bean
    public Exchange createConsultationExchange()
    {
        return new DirectExchange("createConsultation");
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
    public Queue deleteConsultationQueue() {
        return new Queue("deleteConsultation", false);
    }

    @Bean
    public Exchange deleteConsultationExchange()
    {
        return new DirectExchange("deleteConsultation");
    }

    @Bean
    public Binding bindingDeleteConsultation(Queue deleteConsultationQueue, Exchange deleteConsultationExchange)
    {
        return BindingBuilder.bind(deleteConsultationQueue)
                .to(deleteConsultationExchange)
                .with("deleteConsultation")
                .noargs();
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
