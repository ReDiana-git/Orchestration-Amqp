package nl.nl0e0.orchestrationamqp.repository;

import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends org.springframework.data.repository.Repository<MedicalRecord,String> {
    void save(MedicalRecord index);
    List<MedicalRecord> findByOwnerId(Integer ownerId);
}
