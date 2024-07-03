package nl.nl0e0.paymentamqp.service;

import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {
    @Autowired
    PaymentService paymentService;

    @RabbitListener(queues = "createPaymentQueue")
    public void createPayment(MedicalRecord medicalRecord){
        paymentService.createAppointment(medicalRecord);
    }
    @RabbitListener(queues = "deletePaymentQueue")
    public void deletePayment(String string){
        if(string.equals("delete"))
            paymentService.deleteAll();
    }
}
