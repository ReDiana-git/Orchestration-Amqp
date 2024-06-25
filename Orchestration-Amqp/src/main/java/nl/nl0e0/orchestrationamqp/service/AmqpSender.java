package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.orchestrationamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;
    public void createMedicalRecord(MedicalRecord medicalRecord) {
        template.convertAndSend("createAppointmentExchange", "createAppointment", medicalRecord.getAppointmentId());
        template.convertAndSend("createPaymentExchange", "createPayment", medicalRecord.getPaymentId());
        template.convertAndSend("createConsultationExchange", "createConsultation", medicalRecord.getConsultationId());
        template.convertAndSend("createMedicineExchange", "createMedicine", medicalRecord.getMedicineId());
    }
}