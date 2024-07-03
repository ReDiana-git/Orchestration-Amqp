package nl.nl0e0.consultationamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    @Autowired
    ConsultationService consultationService;
    @RabbitListener(queues = "createConsultationQueue")
    public void createConsultation(MedicalRecord medicalRecord){
        consultationService.createAppointment(medicalRecord);
    }
    @RabbitListener(queues = "deleteConsultationQueue")
    public void deleteConsultation(String string){
        if(string.equals("delete"))
            consultationService.deleteAll();
    }
}
