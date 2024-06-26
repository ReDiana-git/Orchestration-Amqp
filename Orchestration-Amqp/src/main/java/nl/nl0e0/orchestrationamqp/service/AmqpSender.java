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
        template.convertAndSend("createAppointmentExchange", "createAppointment", medicalRecord);
        template.convertAndSend("createPaymentExchange", "createPayment", medicalRecord);
        template.convertAndSend("createConsultationExchange", "createConsultation", medicalRecord);
        template.convertAndSend("createMedicineExchange", "createMedicine", medicalRecord);
    }

    public void deleteAll() {
        template.convertAndSend("deleteAppointmentExchange", "deleteAppointment", "delete");
        template.convertAndSend("deletePaymentExchange", "deletePayment", "delete");
        template.convertAndSend("deleteConsultationExchange", "deleteConsultation", "delete");
        template.convertAndSend("deleteMedicineExchange", "deleteMedicine", "delete");
    }
}