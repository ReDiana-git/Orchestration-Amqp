package nl.nl0e0.orchestrationamqp.contract;

import nl.nl0e0.orchestrationamqp.contract.BaseClass;
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
	public void validate_contract() throws Exception {
		// when:
			trigger();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("createAppointment",
					contract(this, "contract.yml"));
			assertThat(response).isNotNull();

		// and:
			assertThat(response.getHeader("amqp_receivedRoutingKey")).isNotNull();
			assertThat(response.getHeader("amqp_receivedRoutingKey").toString()).isEqualTo("createAppointment");

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['ownerId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['petId']").isEqualTo(2);
			assertThatJson(parsedJson).field("['vetId']").isEqualTo(1);
			assertThatJson(parsedJson).field("['appointmentDate']").matches("^\\s*\\S[\\S\\s]*");
	}

}
