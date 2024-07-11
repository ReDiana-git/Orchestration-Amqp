package nl.nl0e0.medicineamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationWithIdDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    MedicineService medicineService;
    public MedicalRecord store;
    @RabbitListener(queues = "createMedicine")
    public void createConsultation(MedicalRecord medicalRecord){
        store = medicalRecord;
        medicineService.createAppointment(medicalRecord);
    }

    @RabbitListener(queues = "deleteMedicine")
    public void deleteConsultation(String string){
        if(string.equals("delete"))
            medicineService.deleteAll();
    }

    @RabbitListener(queues = "updateMedicine")
    public void updateMedicine(UpdateConsultationWithIdDTO dto){
        System.out.println("Receive data from updateMedicine." + "\n" + dto);
        medicineService.updateMedicine(dto);
    }
}
