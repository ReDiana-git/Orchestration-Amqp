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
//    private final ObjectMapper objectMapper;
//
//    public AmqpConfig(ObjectMapper objectMapper){
//        this.objectMapper = objectMapper;
//    }
//    @Bean
//    MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
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
    public Queue createPaymentQueue() {
        return new Queue("createPayment", false);
    }

    @Bean
    public Exchange createPaymentExchange()
    {
        return new DirectExchange("createPayment");
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
        return new DirectExchange("createMedicine");
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
        return new Queue("deleteAppointment", false);
    }

    @Bean
    public Exchange deleteAppointmentExchange()
    {
        return new DirectExchange("deleteAppointment");
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
        return new Queue("deleteMedicine", false);
    }

    @Bean
    public Exchange deleteMedicineExchange()
    {
        return new DirectExchange("deleteMedicine");
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
        return new Queue("deletePayment", false);
    }

    @Bean
    public Exchange deletePaymentExchange()
    {
        return new DirectExchange("deletePayment");
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
    public Queue returnMedicalRecordQueue() {
        return new Queue("returnMedicalRecord", false);
    }

    @Bean
    public Exchange returnMedicalRecordExchange()
    {
        return new DirectExchange("returnMedicalRecord");
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
        return new Queue("returnMedicalRecords", false);
    }

    @Bean
    public Exchange returnMedicalRecordsExchange()
    {
        return new DirectExchange("returnMedicalRecords");
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
    public Binding bindingGetIdByOwner(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("getIdByOwner")
                .noargs();
    }

    @Bean
    public Queue getRecordById2UpdateConsultationQueue() {
        return new Queue("getRecordById2UpdateConsultation", false);
    }

    @Bean
    public Exchange getRecordById2UpdateConsultationExchange()
    {
        return new DirectExchange("getRecordById2UpdateConsultation");
    }

    @Bean
    public Binding bindingGetRecordById2UpdateConsultation(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("getRecordById2UpdateConsultation")
                .noargs();
    }
    @Bean
    public Queue returnMedicalRecord2UpdateConsultationQueue() {
        return new Queue("returnMedicalRecord2UpdateConsultation", false);
    }

    @Bean
    public Exchange returnMedicalRecord2UpdateConsultationExchange()
    {
        return new DirectExchange("returnMedicalRecord2UpdateConsultation");
    }

    @Bean
    public Binding returnMedicalRecord2UpdateConsultation(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("returnMedicalRecord2UpdateConsultation")
                .noargs();
    }

    @Bean
    public Queue updateConsultationQueue() {
        return new Queue("updateConsultation", false);
    }

    @Bean
    public Exchange updateConsultationExchange()
    {
        return new DirectExchange("updateConsultation");
    }

    @Bean
    public Binding bindingUpdateConsultation(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("updateConsultation")
                .noargs();
    }
    @Bean
    public Queue updateMedicineQueue() {
        return new Queue("updateMedicine", false);
    }

    @Bean
    public Exchange updateMedicineExchange()
    {
        return new DirectExchange("updateMedicine");
    }

    @Bean
    public Binding bindingUpdateMedicine(Queue getIdByOwnerQueue, Exchange getIdByOwnerExchange)
    {
        return BindingBuilder.bind(getIdByOwnerQueue)
                .to(getIdByOwnerExchange)
                .with("updateMedicine")
                .noargs();
    }
}
