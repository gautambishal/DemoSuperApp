package com.example.demo.repo;

import com.example.demo.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;

public interface DroneRepository extends JpaRepository<Drone,Integer> {
    Drone getDroneBySerialNumber(String serialNumber);
}
