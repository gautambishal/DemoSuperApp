package com.example.demo.service.impl;

import com.example.demo.models.Drone;
import com.example.demo.repo.DroneRepository;
import com.example.demo.service.DroneService;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    private static final List<String> MODELS= Arrays.asList("Lightweight", "Middleweight", "Cruiserweight", "Heavyweight");
    private final DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public String registerDrone(Drone drone) throws ValidationException {
        validateDronePayload(drone);
        drone.setBatteryPercentage(100);
        this.droneRepository.save(drone);
        return "successfully registered drone";
    }

    private void validateDronePayload(Drone drone) throws ValidationException {
        if(drone.getWeight()>500 || validateDroneModel(drone.getModel())){
            throw new ValidationException("Drone weight greater than 500 grm or drone model is invalid");
        }
    }

    private boolean validateDroneModel(String model) {
        return !MODELS.contains(model);
    }


}
