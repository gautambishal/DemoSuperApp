package com.example.demo.controller;

import com.example.demo.models.Drone;
import com.example.demo.models.Medication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DroneController {

    @GetMapping("/ping")
    public String pingApi(){
        return "Alive!!";
    }
/*     There is no need for UI;
 Prevent the drone from being loaded with more weight that it can carry;
 Prevent the drone from being in LOADING state if the battery level is below 25%;
 Introduce a periodic task to check drone's battery levels and create history/audit event log for
            this.
             Implement Multi-Threading and Lambda expression for Drone processing.*/

    /**
     * registering a drone
     */
    @PostMapping("/registerDrone")
    public ResponseEntity<?> registerDrone(@Valid @RequestBody Drone drone){
        return null;
    }

}
