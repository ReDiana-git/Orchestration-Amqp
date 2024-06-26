package nl.nl0e0.medicineamqp.service;

import nl.nl0e0.medicineamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.medicineamqp.entity.medicine.MedicineEntity;
import nl.nl0e0.medicineamqp.repository.MedicineRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    MedicineRepositroy medicineRepositroy;
    public void createAppointment(MedicalRecord medicalRecord) {
        medicineRepositroy.save(new MedicineEntity(medicalRecord));
    }

    public void deleteAll() {
        medicineRepositroy.deleteAll();
    }
}
