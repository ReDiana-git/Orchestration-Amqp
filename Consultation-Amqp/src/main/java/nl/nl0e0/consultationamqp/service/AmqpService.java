package nl.nl0e0.consultationamqp.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpService {

    @Autowired
    ConsultationService consultationService;
    @RabbitListener(queues = "createConsultationQueue")
    public void createConsultationReceiver(String id){
        consultationService.createAppointment(id);
    }
}
