package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.orchestrationamqp.entity.OwnerNameDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.orchestrationamqp.entity.owner.Owner;
import nl.nl0e0.orchestrationamqp.repository.MedicalRecordRepository;
import nl.nl0e0.orchestrationamqp.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrchestrationService {
    @Autowired
    AmqpSender amqpSender;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    OwnerRepository ownerRepository;

    public void checkCreateAppointmentDTOValidation(CreateAppointmentDTO createAppointMentDTO) {
        String notBeNull = " should not be null.";
        if(createAppointMentDTO.getAppointmentDate() == null)
            throw new NullPointerException("Appointment Date" + notBeNull);
        if(createAppointMentDTO.getPetId() == null)
            throw new NullPointerException("Pet ID" + notBeNull);
        if(createAppointMentDTO.getOwnerId() == null)
            throw new NullPointerException("Owner ID" + notBeNull);
        if(createAppointMentDTO.getVetId() == null)
            throw new NullPointerException("Vet ID" + notBeNull);
    }

    public MedicalRecord createAppointment(CreateAppointmentDTO createAppointMentDTO) {
        MedicalRecord medicalRecord = new MedicalRecord(createAppointMentDTO);
        medicalRecordRepository.save(medicalRecord);
        amqpSender.createMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    public void deleteAll() {
        amqpSender.deleteAll();
    }

    public void getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
        Owner owner = ownerRepository.findByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
        amqpSender.findByOwnerId(owner.getId());
    }
}
