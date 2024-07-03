package nl.nl0e0.appointmentamqp.service;

import java.util.List;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.appointmentamqp.repositroy.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> findByOwnerId(Integer ownerId) {
        return medicalRecordRepository.findByOwnerId(ownerId);
    }
}
