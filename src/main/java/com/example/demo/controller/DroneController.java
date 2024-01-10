package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.models.Drone;
import com.example.demo.models.Medication;
import com.example.demo.service.DroneService;
import com.example.demo.validator.DTOValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DroneController {
    private final DroneService droneService;
    private final ObjectMapper objectMapper;

    private final DTOValidator dtoValidator;

    public DroneController(DroneService droneService, ObjectMapper objectMapper, DTOValidator dtoValidator) {
        this.droneService = droneService;
        this.objectMapper = objectMapper;
        this.dtoValidator = dtoValidator;
    }

    @GetMapping("/ping")
    public String pingApi(){
        return "Alive!!";
    }


    /**
     * registering a drone
     */
    @PostMapping("/registerDrone")
    public ResponseEntity<Response> registerDrone(@Valid @RequestBody Drone drone) {
        return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(),this.droneService.registerDrone(drone),null));
    }

    /**
     * loading a drone with medication items.
     */
    @PostMapping(value = "/loadMedication/{serialNo}")
    public ResponseEntity<Response> loadingDrone(@PathVariable("serialNo") String serialNumber,
                                                 @RequestParam("medication") String medication) throws MethodArgumentNotValidException, JsonProcessingException {

            List<Medication> meds = this.objectMapper.readValue(medication, new TypeReference<List<Medication>>() {});
            validateMedicationPayload(meds);
            return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(),this.droneService.loadDrone(serialNumber,meds),null));
    }

    /**
     * check drone battery level for a given drone;
     */
    @GetMapping("/getBatteryPercentage/{serialNo}")
    public ResponseEntity<Response> getDroneBatteryPercentage(@PathVariable("serialNo") String serialNo){
        return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), this.droneService.getDroneBatteryPercentage(serialNo),null));
    }

    /**
     * get available drones
     * @return
     */
    @GetMapping("/getAvailableDrones")
    public ResponseEntity<Response> getAvailableDrones(){
        return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), "data successfully fetched",this.droneService.getAvailableDrones()));
    }


    private void validateMedicationPayload(List<Medication> meds) throws MethodArgumentNotValidException {
        for (Medication med : meds) {
            this.dtoValidator.validate(med);
        }
    }
}
