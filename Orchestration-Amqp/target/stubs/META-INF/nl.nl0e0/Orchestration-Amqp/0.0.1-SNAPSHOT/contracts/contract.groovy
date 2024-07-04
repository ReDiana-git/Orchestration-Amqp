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
        sentTo "createAppointment"
        body([
                ownerId: 1,
                petId: 2,
                vetId: 1,
                appointmentDate: $(regex("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.\\d+)?Z?"))
        ])
        headers {
            header("amqp_receivedRoutingKey", "createAppointment")
        }
    }
}



