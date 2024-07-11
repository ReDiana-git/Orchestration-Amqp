package nl.nl0e0.orchestrationamqp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationWithIdDTO;
import nl.nl0e0.petclinicentity.owner.OwnerNameDTO;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.owner.Owner;
import nl.nl0e0.orchestrationamqp.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrchestrationService {
    @Autowired
    AmqpSender amqpSender;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

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


    public void saveReturnMedicalRecords(List<MedicalRecord> medicalRecords) {
        redisTemplate.opsForValue().set(medicalRecords.get(0).getOwnerId().toString(), medicalRecords);
        System.out.println("Store data to Redis success.");
    }

    public List<MedicalRecord> reGetAppointmentsByOwnerName(OwnerNameDTO ownerNameDTO) {
        Owner owner = ownerRepository.findByFullName(ownerNameDTO.getFirstName(), ownerNameDTO.getLastName());
        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) redisTemplate.opsForValue().getAndDelete(owner.getId().toString());
        for(MedicalRecord medicalRecord: medicalRecords){
            System.out.println(medicalRecord);
        }
        return medicalRecords;
    }

    public void checkConsultationByName(String firstName, String lastName) {
        Owner owner = ownerRepository.findByFullName(firstName, lastName);
        amqpSender.findByOwnerId(owner.getId());
    }

    public void updateConsultation(UpdateConsultationDTO updateConsultationDTO) {
        redisTemplate.opsForValue().set(updateConsultationDTO.getRecordId(),updateConsultationDTO);
        amqpSender.findRecordById2UpdateConsultation(updateConsultationDTO.getRecordId());

    }

    public void updateConsultationByMedicalRecord(MedicalRecord medicalRecord) {
        UpdateConsultationDTO updateConsultationDTO =(UpdateConsultationDTO) redisTemplate.opsForValue().getAndDelete(medicalRecord.getId());
        UpdateConsultationWithIdDTO dto = new UpdateConsultationWithIdDTO(updateConsultationDTO, medicalRecord.getMedicineId(), medicalRecord.getConsultationId());
        amqpSender.updateConsultation(dto);
        amqpSender.updateMedicine(dto);
    }
}
