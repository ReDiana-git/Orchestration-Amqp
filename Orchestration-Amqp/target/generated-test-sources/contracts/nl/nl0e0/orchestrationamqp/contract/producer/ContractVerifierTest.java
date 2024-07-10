package nl.nl0e0.orchestrationamqp.contract.producer;

import nl.nl0e0.orchestrationamqp.contract.producer.BaseClass;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.inject.Inject;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.messaging.util.ContractVerifierMessagingUtil.headers;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.fileToBytes;

@SuppressWarnings("rawtypes")
public class ContractVerifierTest extends BaseClass {
	@Inject ContractVerifierMessaging contractVerifierMessaging;
	@Inject ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Test
	public void validate_createAppointmentContract() throws Exception {
		// when:
			trigger();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("createAppointment",
					contract(this, "createAppointmentContract.yml"));
			assertThat(response).isNotNull();

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['appointmentDate']").isEqualTo("2024-07-05T14:45:33.851107");
	}

	@Test
	public void validate_createConsultationContract() throws Exception {
		// when:
			trigger1();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("createConsultation",
					contract(this, "createConsultationContract.yml"));
			assertThat(response).isNotNull();

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['id']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['appointmentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['consultationId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['paymentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['medicineId']").matches("^\\s*\\S[\\S\\s]*");
	}

	@Test
	public void validate_createMedicineContract() throws Exception {
		// when:
			trigger1();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("createMedicine",
					contract(this, "createMedicineContract.yml"));
			assertThat(response).isNotNull();

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['id']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['appointmentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['consultationId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['paymentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['medicineId']").matches("^\\s*\\S[\\S\\s]*");
	}

	@Test
	public void validate_createPaymentContract() throws Exception {
		// when:
			trigger1();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("createPayment",
					contract(this, "createPaymentContract.yml"));
			assertThat(response).isNotNull();

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['id']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['appointmentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['consultationId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['paymentId']").matches("^\\s*\\S[\\S\\s]*");
			assertThatJson(parsedJson).field("['medicineId']").matches("^\\s*\\S[\\S\\s]*");
	}

}
