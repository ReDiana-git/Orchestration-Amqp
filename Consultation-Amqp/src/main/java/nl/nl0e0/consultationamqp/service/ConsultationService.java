package nl.nl0e0.consultationamqp.service;

import lombok.Setter;
import nl.nl0e0.consultationamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.consultationamqp.entity.consultation.ConsultationEntity;
import nl.nl0e0.consultationamqp.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    public void createAppointment(MedicalRecord medicalRecord) {
        consultationRepository.save(new ConsultationEntity(medicalRecord));
    }


    public void deleteAll() {
        consultationRepository.deleteAll();
    }
}
