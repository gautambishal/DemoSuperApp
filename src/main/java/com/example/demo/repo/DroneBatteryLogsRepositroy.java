package com.example.demo.repo;

import com.example.demo.models.DroneBatteryLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneBatteryLogsRepositroy extends JpaRepository<DroneBatteryLogs,Integer> {
}
