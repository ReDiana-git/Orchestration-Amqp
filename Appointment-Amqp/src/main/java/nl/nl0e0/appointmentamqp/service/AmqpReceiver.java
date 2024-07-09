package nl.nl0e0.appointmentamqp.service;


import nl.nl0e0.petclinicentity.appointment.AppointmentEntity;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
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

    public CreateAppointmentDTO store = new CreateAppointmentDTO();

    @RabbitListener(queues = "createAppointment")
    public void createAppointment(CreateAppointmentDTO createAppointmentDTO){
        this.store = createAppointmentDTO;
        appointmentService.createAppointment(createAppointmentDTO);
    }

    @RabbitListener(queues = "deleteAppointment")
    public void deleteAppointment(String string){
        if(string.equals("delete"))
            appointmentService.deleteAll();
    }

    @RabbitListener(queues = "getIdByOwner")
    public void getAppointmentByOwner(Integer id){
        List<MedicalRecord> medicalRecords = medicalRecordService.findByOwnerId(id);
        amqpSender.returnMedicalRecords(medicalRecords);
    }

}
