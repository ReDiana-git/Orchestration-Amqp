package nl.nl0e0.appointmentamqp.service;


import nl.nl0e0.appointmentamqp.entity.appointment.AppointmentEntity;
import nl.nl0e0.appointmentamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.appointmentamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.appointmentamqp.repositroy.MedicalRecordRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmqpReceiver {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    AmqpSender amqpSender;

    @RabbitListener(queues = "createAppointmentQueue")
    public void createAppointment(CreateAppointmentDTO createAppointmentDTO){
        appointmentService.createAppointment(createAppointmentDTO);
    }

    @RabbitListener(queues = "deleteAppointmentQueue")
    public void deleteAppointment(String string){
        if(string.equals("delete"))
            appointmentService.deleteAll();
    }

    @RabbitListener(queues = "getIdByOwnerQueue")
    public void getAppointmentByOwner(Integer id){
        List<MedicalRecord> medicalRecords = medicalRecordService.findByOwnerId(id);
        amqpSender.returnMedicalRecords(medicalRecords);
    }
}
