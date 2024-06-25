package nl.nl0e0.orchestrationamqp.service;

import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;

public class SuccessActionAlert {
    public void createAppointmentAlert(MedicalRecord medicalRecord){
        System.out.println("-----------------");
        System.out.println("Create Appointment Success.");
        System.out.println(medicalRecord);
        System.out.println("-----------------");
    }
}
