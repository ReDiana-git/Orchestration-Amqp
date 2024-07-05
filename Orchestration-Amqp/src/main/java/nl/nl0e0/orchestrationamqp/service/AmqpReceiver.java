package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmqpReceiver {
    @Autowired
    OrchestrationService orchestrationService;

    public MedicalRecord medicalStore;

    @RabbitListener(queues = "returnMedicalRecordQueue")
    public void returnMedicalRecord(MedicalRecord medicalRecord){
        medicalStore = medicalRecord;
        orchestrationService.createPCM(medicalRecord);
    }

    @RabbitListener(queues = "returnMedicalRecordsQueue")
    public void returnMedicalRecords(List<MedicalRecord> medicalRecords){
        orchestrationService.saveReturnMedicalRecords(medicalRecords);
    }
}