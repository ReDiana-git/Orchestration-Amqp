package nl.nl0e0.orchestrationamqp.controller;

import nl.nl0e0.orchestrationamqp.entity.OwnerNameDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.CreateAppointmentDTO;
import nl.nl0e0.orchestrationamqp.entity.appointment.MedicalRecord;
import nl.nl0e0.orchestrationamqp.service.OrchestrationService;
import nl.nl0e0.orchestrationamqp.service.SuccessActionAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class OrchestrationController {
    @Autowired
    OrchestrationService orchestrationService;
    SuccessActionAlert successActionAlert = new SuccessActionAlert();


    // 建立新的預約單號
    @PostMapping("/appointment/createAppointment")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDTO createAppointMentDTO){

        try{
            orchestrationService.checkCreateAppointmentDTOValidation(createAppointMentDTO);
            MedicalRecord medicalRecord = orchestrationService.createAppointment(createAppointMentDTO);
            successActionAlert.createAppointmentAlert(medicalRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord);
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定義錯誤訊息和HTTP狀態碼的ResponseEntity
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @PostMapping("/appointment/createAppointments")
    public Mono<ResponseEntity<MedicalRecord>> createAppointments(@RequestBody CreateAppointmentDTO createAppointMentDTO){
        try{
            orchestrationService.checkCreateAppointmentDTOValidation(createAppointMentDTO);
            MedicalRecord medicalRecord = orchestrationService.createAppointment(createAppointMentDTO);
            successActionAlert.createAppointmentAlert(medicalRecord);
            return Mono.create(responseEntityMonoSink -> {
                responseEntityMonoSink.success(ResponseEntity.status(HttpStatus.CREATED).body(medicalRecord));
            });
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定義錯誤訊息和HTTP狀態碼的ResponseEntity
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            return Mono.create(responseEntityMonoSink -> {
                responseEntityMonoSink.error(exception);
            });
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteAll(){
        orchestrationService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/appointment/getAppointments")
    public ResponseEntity<?> getAppointmentByOwnerName(@RequestBody OwnerNameDTO ownerNameDTO){
        orchestrationService.getAppointmentsByOwnerName(ownerNameDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
