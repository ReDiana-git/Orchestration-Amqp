package nl.nl0e0.appointmentamqp.service;

import nl.nl0e0.petclinicentity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
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
        appointmentRepository.save(new AppointmentEntity(medicalRecord, createAppointmentDTO));
        amqpSender.returnMedicalRecord(medicalRecord);
    }

    public void deleteAll() {
        medicalRecordRepository.deleteAll();
        appointmentRepository.deleteAll();
    }

    public void getRecordById2UpdateConsultation(String recordId) {
        amqpSender.returnMedicalRecord2UpdateConsultation(medicalRecordRepository.findById(recordId));
    }
}
