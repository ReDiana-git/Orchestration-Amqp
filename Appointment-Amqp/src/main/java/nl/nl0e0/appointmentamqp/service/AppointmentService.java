package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.appointmentamqp.entity.appointment.AppointmentEntity;
import nl.nl0e0.appointmentamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.appointmentamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.appointmentamqp.repositroy.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    public void createAppointment(MedicalRecord medicalRecord) {
        appointmentRepository.save(new AppointmentEntity(medicalRecord));
    }

    public void deleteAll() {
        appointmentRepository.deleteAll();
    }
}
