package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should send a message when event occurs"
    label "send_message_to_orchestrator"

    input {
        triggeredBy("trigger()")
    }

    outputMessage {
        sentTo "returnMedicalRecord"
        body([
                ownerId: 1,
                petId: 2,
                vetId: 1,
                id: $(anyNonBlankString()),
                appointmentId: $(anyNonBlankString()),
                consultationId: $(anyNonBlankString()),
                paymentId: $(anyNonBlankString()),
                medicineId: $(anyNonBlankString())
        ])
        headers {
            header("amqp_receivedRoutingKey", "returnMedicalRecord")
        }
    }
}



