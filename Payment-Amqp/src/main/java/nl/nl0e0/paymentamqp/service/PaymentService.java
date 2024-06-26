package nl.nl0e0.paymentamqp.service;

import nl.nl0e0.paymentamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.paymentamqp.entity.payment.PaymentEntity;
import nl.nl0e0.paymentamqp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepositroy;

    public void createAppointment(MedicalRecord medicalRecord) {
        paymentRepositroy.save(new PaymentEntity(medicalRecord));
    }

    public void deleteAll() {
        paymentRepositroy.deleteAll();
    }
}
