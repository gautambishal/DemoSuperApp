package com.example.demo.repo;

import com.example.demo.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone,Integer> {
}
