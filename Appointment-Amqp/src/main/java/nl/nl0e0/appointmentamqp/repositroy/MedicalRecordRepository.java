package nl.nl0e0.appointmentamqp.repositroy;

import jakarta.transaction.Transactional;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends org.springframework.data.repository.Repository<MedicalRecord,String> {
    void save(MedicalRecord index);
    List<MedicalRecord> findByOwnerId(Integer ownerId);
    MedicalRecord findById(String id);
    void deleteAll();

    @Modifying
    @Transactional
    @Query("UPDATE MedicalRecord medicineRecord SET medicineRecord.state = :state WHERE medicineRecord.id = :recordId")
    void updateState(@Param("state") String state, @Param("recordId") String id);

}
