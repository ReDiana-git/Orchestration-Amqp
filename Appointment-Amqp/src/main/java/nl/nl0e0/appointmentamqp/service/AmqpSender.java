package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.appointmentamqp.entity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmqpSender {
    @Autowired
    private RabbitTemplate template;
    public void returnMedicalRecord(MedicalRecord medicalRecord){
        template.convertAndSend("returnMedicalRecordExchange", "returnMedicalRecord", medicalRecord);
    }

    public void returnMedicalRecords(List<MedicalRecord> medicalRecords) {
        template.convertAndSend("returnMedicalRecordsExchange", "returnMedicalRecords", medicalRecords);
    }
}
