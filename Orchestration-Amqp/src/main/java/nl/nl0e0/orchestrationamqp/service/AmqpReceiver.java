package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    OrchestrationService orchestrationService;

    @RabbitListener(queues = "returnMedicalRecordQueue")
    public void returnMedicalRecord(MedicalRecord medicalRecord){
        orchestrationService.createPCM(medicalRecord);
    }
}