package nl.nl0e0.paymentamqp.entity.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import nl.nl0e0.paymentamqp.entity.appointment.MedicalRecord;

@Entity
@Setter
@Getter
@Table(name = "payment")
public class PaymentEntity implements Serializable {

	public boolean getPaymentStatus() {
		return paymentStatus;
	}
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "price")
	private Integer price;
	@Column(name = "payment_status")
	private boolean paymentStatus;
	public PaymentEntity(MedicalRecord medicalRecord){
		this.id = medicalRecord.getPaymentId();
		this.paymentStatus = false;
	}
	public PaymentEntity(String id){
		this.id = id;
		this.price = 0;
		this.paymentStatus = false;
	}
	public PaymentEntity(){

	}
}
