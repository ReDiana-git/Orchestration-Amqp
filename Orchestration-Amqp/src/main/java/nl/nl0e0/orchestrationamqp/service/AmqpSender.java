package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;
    public void createMedicalRecord(CreateAppointmentDTO createAppointMentDTO) {
        template.convertAndSend("createAppointment", "createAppointment", createAppointMentDTO);
    }
    public void createPCM(MedicalRecord medicalRecord) {
        template.convertAndSend("createPayment", "createPayment", medicalRecord);
        template.convertAndSend("createConsultation", "createConsultation", medicalRecord);
        template.convertAndSend("createMedicine", "createMedicine", medicalRecord);
    }

    public void deleteAll() {
        template.convertAndSend("deleteAppointmentExchange", "deleteAppointment", "delete");
        template.convertAndSend("deletePaymentExchange", "deletePayment", "delete");
        template.convertAndSend("deleteConsultationExchange", "deleteConsultation", "delete");
        template.convertAndSend("deleteMedicineExchange", "deleteMedicine", "delete");
    }

    public void findByOwnerId(Integer id) {
        System.out.println("Send data to getIdByOwnerExchange.");
        template.convertAndSend("getIdByOwner", "getIdByOwner", id);
    }
}