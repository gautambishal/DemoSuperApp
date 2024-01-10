package com.example.demo.service;

import com.example.demo.models.Drone;
import com.example.demo.models.Medication;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface DroneService {
    String registerDrone(Drone drone);

    String loadDrone(String serialNumber, List<Medication> medication);
}
