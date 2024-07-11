package nl.nl0e0.consultationamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.ConsultationEntity;
import nl.nl0e0.consultationamqp.repository.ConsultationRepository;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationWithIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {
    @Autowired
    ConsultationRepository consultationRepository;
    public void createConsultation(MedicalRecord medicalRecord) {
        consultationRepository.save(new ConsultationEntity(medicalRecord));
    }


    public void deleteAll() {
        consultationRepository.deleteAll();
    }

    public void updateConsultation(UpdateConsultationWithIdDTO dto) {
        consultationRepository.updateSymptom(dto.getConsultationId(), dto.getSymptom());
    }
}
