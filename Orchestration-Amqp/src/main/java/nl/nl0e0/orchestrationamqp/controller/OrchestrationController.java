package nl.nl0e0.orchestrationamqp.controller;

import nl.nl0e0.petclinicentity.consultation.CheckConsultationDTO;
import nl.nl0e0.petclinicentity.consultation.UpdateConsultationDTO;
import nl.nl0e0.petclinicentity.owner.OwnerNameDTO;
import nl.nl0e0.petclinicentity.appointment.CreateAppointmentDTO;
import nl.nl0e0.petclinicentity.appointment.MedicalRecord;
import nl.nl0e0.orchestrationamqp.service.OrchestrationService;
import nl.nl0e0.orchestrationamqp.service.SuccessActionAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/appointment/createAppointments")
    public ResponseEntity<?> createAppointment(@RequestBody CreateAppointmentDTO createAppointMentDTO){

        try{
            orchestrationService.checkCreateAppointmentDTOValidation(createAppointMentDTO);
            orchestrationService.createAppointment(createAppointMentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception exception){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", exception.getMessage());

            // 返回包含自定義錯誤訊息和HTTP狀態碼的ResponseEntity
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }
    @GetMapping("/appointment/consultationByName/")
    public ResponseEntity<?> checkConsultationByName(@RequestParam("firstname") String firstName, @RequestParam("lastName") String lastName){
        orchestrationService.checkConsultationByName(firstName, lastName);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteAll(){
        orchestrationService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/appointment/getAppointments")
    public ResponseEntity<?> getAppointmentsByOwnerName(@RequestParam String firstName, @RequestParam String lastName){
        OwnerNameDTO ownerNameDTO = new OwnerNameDTO();
        ownerNameDTO.setFirstName(firstName);
        ownerNameDTO.setLastName(lastName);
        orchestrationService.getAppointmentsByOwnerName(ownerNameDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/appointment/reGetAppointments")
    public ResponseEntity<?> reGetAppointmentsByOwnerName(@RequestParam String firstName, @RequestParam String lastName){
        OwnerNameDTO ownerNameDTO = new OwnerNameDTO();
        ownerNameDTO.setFirstName(firstName);
        ownerNameDTO.setLastName(lastName);
        List<MedicalRecord> medicalRecords = orchestrationService.reGetAppointmentsByOwnerName(ownerNameDTO);
        return ResponseEntity.status(HttpStatus.OK).body(medicalRecords);
    }
    @PostMapping("/appointment/updateConsultation")
    public ResponseEntity<?> updateConsultation(@RequestBody UpdateConsultationDTO updateConsultationDTO){
        System.out.println("updateConsultationDTO Object in Controller\n" + updateConsultationDTO);
        orchestrationService.updateConsultation(updateConsultationDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
