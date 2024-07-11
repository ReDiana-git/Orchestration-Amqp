package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationWithIdDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        template.convertAndSend("deleteAppointment", "deleteAppointment", "delete");
        template.convertAndSend("deletePayment", "deletePayment", "delete");
        template.convertAndSend("deleteConsultation", "deleteConsultation", "delete");
        template.convertAndSend("deleteMedicine", "deleteMedicine", "delete");
    }

    public void findByOwnerId(Integer id) {
        System.out.println("Send data to getIdByOwnerExchange.");
        template.convertAndSend("getIdByOwner", "getIdByOwner", id);
    }


    public void findRecordById2UpdateConsultation(String recordId) {
        System.out.println("Send data to getRecordById2UpdateConsultation.");
        template.convertAndSend("getRecordById2UpdateConsultation","getRecordById2UpdateConsultation", recordId);
    }

    public void updateConsultation(UpdateConsultationWithIdDTO dto) {
        System.out.println("Send data to updateConsultation.");
        template.convertAndSend("updateConsultation", "updateConsultation", dto);
    }

    public void updateMedicine(UpdateConsultationWithIdDTO dto) {
        System.out.println("Send data to updateMedicine.");
        template.convertAndSend("updateMedicine", "updateMedicine", dto);
    }
}