package nl.nl0e0.appointmentamqp.service;


import nl.nl0e0.appointmentamqp.entity.appointment.AppointmentEntity;
import nl.nl0e0.appointmentamqp.entity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    AppointmentService appointmentService;

    @RabbitListener(queues = "createAppointmentQueue")
    public void createConsultationReceiver(String id){
        appointmentService.createAppointment(id);
    }
}
