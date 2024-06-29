package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.appointmentamqp.entity.appointment.AppointmentEntity;
import nl.nl0e0.appointmentamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.appointmentamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.appointmentamqp.repositroy.AppointmentRepository;
import nl.nl0e0.appointmentamqp.repositroy.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    AmqpSender amqpSender;
    public void createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        MedicalRecord medicalRecord = new MedicalRecord(createAppointmentDTO);
        medicalRecordRepository.save(medicalRecord);
        appointmentRepository.save(new AppointmentEntity(medicalRecord));
        amqpSender.returnMedicalRecord(medicalRecord);
    }

    public void deleteAll() {
        appointmentRepository.deleteAll();
    }
}
