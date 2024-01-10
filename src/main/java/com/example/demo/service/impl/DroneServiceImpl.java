package com.example.demo.service.impl;

import com.example.demo.exception.DroneException;
import com.example.demo.models.Drone;
import com.example.demo.models.DroneMedications;
import com.example.demo.models.Medication;
import com.example.demo.models.State;
import com.example.demo.repo.DroneMedicationsRepository;
import com.example.demo.repo.DroneRepository;
import com.example.demo.repo.MedicationRepository;
import com.example.demo.service.DroneService;
import com.example.demo.service.FileService;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    private static final String UPLOAD_LOCATION = "uploads";
    private static final List<String> MODELS= Arrays.asList("Lightweight", "Middleweight", "Cruiserweight", "Heavyweight");
    private final DroneRepository droneRepository;
    private final FileService fileService;
    private final MedicationRepository medicationRepository;

    private final DroneMedicationsRepository droneMedicationsRepository;

    public DroneServiceImpl(DroneRepository droneRepository, FileService fileService, MedicationRepository medicationRepository, DroneMedicationsRepository droneMedicationsRepository) {
        this.droneRepository = droneRepository;
        this.fileService = fileService;
        this.medicationRepository = medicationRepository;
        this.droneMedicationsRepository = droneMedicationsRepository;
    }

    @Override
    public String registerDrone(Drone drone){
        this.validateDronePayload(drone);
        drone.setBatteryPercentage(100);
        drone.setState(State.IDLE);
        this.droneRepository.save(drone);
        return "successfully registered drone";
    }

    @Override
    public String loadDrone(String serialNumber,  List<Medication> medication) {
        Drone drone = this.validateMedicationPayload(medication,serialNumber);
        drone.setState(State.LOADING);
        medication.forEach(med -> this.uploadAndLoadMedicationFiles(med,drone));
        return "successfully loaded";
    }

    @Override
    public String getDroneBatteryPercentage(String serialNo) {
        return this.droneRepository.getDroneBySerialNumber(serialNo).getBatteryPercentage().toString();
    }

    @Override
    public List<Drone> getAvailableDrones() {
//        return this.droneRepository.findById();
        return this.droneRepository.getAllByState(State.IDLE);
    }

    private synchronized void uploadAndLoadMedicationFiles(Medication medication,Drone drone) {
        String path = medication.getPath();
        byte[] uploadFile = this.base64ToMultipartFile(path);
        String fileName = UPLOAD_LOCATION.concat(FileSystems.getDefault().getSeparator()).concat(medication.getName()+LocalDateTime.now().toString());
        medication.setPath(fileService.uploadFile(uploadFile,fileName));
        this.saveDroneMedicationDetails(medication,drone);
    }

    private void saveDroneMedicationDetails(Medication medication, Drone drone) {
        drone.setState(State.LOADED);
        Medication med = this.medicationRepository.save(medication);

        DroneMedications droneMedications = new DroneMedications();
        droneMedications.setMedication(med);
        droneMedications.setDrone(drone);

        this.droneMedicationsRepository.save(droneMedications);
    }

    private byte[] base64ToMultipartFile(String base64) {
        return  Base64.getDecoder().decode(base64);
    }



    private Drone validateMedicationPayload(List<Medication> medication,String serialNumber) {

        int totalGmInWeight = medication.stream().mapToInt(Medication::getWeight).sum();
        if(totalGmInWeight>=500){
            throw new DroneException("Drone cannot carry more weight in medication.");
        }
        Drone drone = this.droneRepository.getDroneBySerialNumber(serialNumber);
        if(drone.getBatteryPercentage()<25){
            throw new DroneException("Drone cannot be converted to loading state.");
        }
        return drone;
    }

    private void validateDronePayload(Drone drone){
        if(drone.getWeight()>500 || validateDroneModel(drone.getModel())){
            throw new DroneException("Drone weight greater than 500 grm or drone model is invalid");
        }
    }

    private boolean validateDroneModel(String model) {
        return !MODELS.contains(model);
    }


}
