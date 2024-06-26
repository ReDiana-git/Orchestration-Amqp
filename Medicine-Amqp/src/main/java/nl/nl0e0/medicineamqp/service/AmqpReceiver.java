package nl.nl0e0.medicineamqp.service;

import nl.nl0e0.medicineamqp.entity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    MedicineService medicineService;
    @RabbitListener(queues = "createMedicineQueue")
    public void createConsultation(MedicalRecord medicalRecord){
        medicineService.createAppointment(medicalRecord);
    }

    @RabbitListener(queues = "deleteMedicineQueue")
    public void deleteConsultation(String string){
        if(string.equals("delete"))
            medicineService.deleteAll();
    }
}
