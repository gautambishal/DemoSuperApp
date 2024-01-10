package com.example.demo.repo;

import com.example.demo.models.DroneMedications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneMedicationsRepository extends JpaRepository<DroneMedications,Integer> {
}
