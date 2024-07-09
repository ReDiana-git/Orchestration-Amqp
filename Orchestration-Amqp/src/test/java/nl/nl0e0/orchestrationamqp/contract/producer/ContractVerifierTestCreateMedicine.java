package nl.nl0e0.orchestrationamqp.contract.producer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@AutoConfigureMessageVerifier
@ContextConfiguration(classes = BaseClass.class)
public class ContractVerifierTestCreateMedicine extends BaseClass{
    @Test
    public void validate_createMedicineContract() throws Exception {
        trigger1(); // 触发相应的 trigger 方法
    }
}
