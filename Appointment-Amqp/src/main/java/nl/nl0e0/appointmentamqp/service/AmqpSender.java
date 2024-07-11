package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;
    public void returnMedicalRecord(MedicalRecord medicalRecord){
        template.convertAndSend("returnMedicalRecord", "returnMedicalRecord", medicalRecord);
    }

    public void returnMedicalRecords(List<MedicalRecord> medicalRecords) {
        System.out.println("Send data to returnMedicalRecordsQueue success.");
        template.convertAndSend("returnMedicalRecords", "returnMedicalRecords", medicalRecords);
    }

    public void returnMedicalRecord2UpdateConsultation(MedicalRecord record) {
        System.out.println("Send a message to returnMedicalRecord2UpdateConsultation." + record);
        template.convertAndSend("returnMedicalRecord2UpdateConsultation", "returnMedicalRecord2UpdateConsultation", record);
    }
}
