package nl.nl0e0.orchestrationamqp.config;

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
        return new TopicExchange("createConsultation");
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
        return new Queue("createPayment", false);
    }

    @Bean
    public Exchange createPaymentExchange()
    {
        return new TopicExchange("createPayment");
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
        return new Queue("createMedicine", false);
    }

    @Bean
    public Exchange createMedicineExchange()
    {
        return new TopicExchange("createMedicine");
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
        return new Queue("createAppointment", false);
    }

    @Bean
    public Exchange createAppointmentExchange()
    {
        return new DirectExchange("createAppointment");
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
    public Queue deleteMedicineQueue() {
        return new Queue("deleteMedicineQueue", false);
    }

    @Bean
    public Exchange deleteMedicineExchange()
    {
        return new DirectExchange("deleteMedicineExchange");
    }

    @Bean
    public Binding bindingDeleteMedicine(Queue deleteMedicineQueue, Exchange deleteMedicineExchange)
    {
        return BindingBuilder.bind(deleteMedicineQueue)
                .to(deleteMedicineExchange)
                .with("deleteMedicine")
                .noargs();
    }
    @Bean
    public Queue deletePaymentQueue() {
        return new Queue("deletePaymentQueue", false);
    }

    @Bean
    public Exchange deletePaymentExchange()
    {
        return new DirectExchange("deletePaymentExchange");
    }

    @Bean
    public Binding bindingDeletePayment(Queue deletePaymentQueue, Exchange deletePaymentExchange)
    {
        return BindingBuilder.bind(deletePaymentQueue)
                .to(deletePaymentExchange)
                .with("deletePayment")
                .noargs();
    }
    @Bean
    public Queue deleteConsultationQueue() {
        return new Queue("deleteConsultationQueue", false);
    }

    @Bean
    public Exchange deleteConsultationExchange()
    {
        return new DirectExchange("deleteConsultationExchange");
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
    public Queue getIdByOwnerQueue() {
        return new Queue("getIdByOwner", false);
    }

    @Bean
    public Exchange getIdByOwnerExchange()
    {
        return new DirectExchange("getIdByOwner");
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
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}