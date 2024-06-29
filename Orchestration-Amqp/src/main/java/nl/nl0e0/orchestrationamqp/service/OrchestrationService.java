package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.orchestrationamqp.entity.OwnerNameDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.orchestrationamqp.entity.owner.Owner;
import nl.nl0e0.orchestrationamqp.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrchestrationService {
    @Autowired
    AmqpSender amqpSender;
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

    public void createAppointment(CreateAppointmentDTO createAppointMentDTO) {
        amqpSender.createMedicalRecord(createAppointMentDTO);
    }
    public void createPCM(MedicalRecord medicalRecord) {
        amqpSender.createPCM(medicalRecord);
    }
    public void deleteAll() {
        amqpSender.deleteAll();
    }

    public void getAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
        Owner owner = ownerRepository.findByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
        amqpSender.findByOwnerId(owner.getId());
    }


}
