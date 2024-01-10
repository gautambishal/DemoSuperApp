package com.example.demo.repo;

import com.example.demo.models.Drone;
import com.example.demo.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone,Integer> {
    Drone getDroneBySerialNumber(String serialNumber);

    List<Drone> getAllByState(State state);
}
