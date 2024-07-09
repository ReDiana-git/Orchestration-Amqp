package nl.nl0e0.orchestrationamqp.contract.producer;
import nl.nl0e0.orchestrationamqp.OrchestrationAmqpApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureMessageVerifier
@ContextConfiguration(classes = {BaseClass.class, OrchestrationAmqpApplication.class})
public class ContractVerifierTestCreateAppointment extends BaseClass{
    @Test
    public void validate_createAppointmentContract() throws Exception {
        trigger(); // 或者是触发其他合适的 trigger 方法
    }
}
