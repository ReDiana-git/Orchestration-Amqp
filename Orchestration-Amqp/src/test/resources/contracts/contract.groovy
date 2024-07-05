package contracts

import org.springframework.cloud.contract.spec.Contract

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

Contract.make {
    description "should send a message when event occurs"
    label "event_occurred"

    input {
        triggeredBy("trigger()")
    }

    outputMessage {
        sentTo "createAppointmentQueue"
        body([
                ownerId: 1,
                petId: 2,
                vetId: 1,
                appointmentDate: "2024-07-05T14:45:33.851107"
        ])
        headers {
            header("amqp_receivedRoutingKey", "createAppointment")
        }
    }
}



