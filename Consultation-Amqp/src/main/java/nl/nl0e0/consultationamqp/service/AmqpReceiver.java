package nl.nl0e0.consultationamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationWithIdDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    @Autowired
    ConsultationService consultationService;
    public MedicalRecord store;
    @RabbitListener(queues = "createConsultation")
    public void createConsultation(MedicalRecord medicalRecord){
        store = medicalRecord;
        consultationService.createConsultation(medicalRecord);
    }
    @RabbitListener(queues = "deleteConsultation")
    public void deleteConsultation(String string){
        if(string.equals("delete"))
            consultationService.deleteAll();
    }

    @RabbitListener(queues = "updateConsultation")
    public void updateConsultation(UpdateConsultationWithIdDTO dto) {
        System.out.println("Receive data from updateConsultation.");
        consultationService.updateConsultation(dto);
    }
}
