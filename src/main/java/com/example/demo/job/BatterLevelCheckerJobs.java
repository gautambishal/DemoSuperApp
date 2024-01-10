package com.example.demo.job;

import com.example.demo.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BatterLevelCheckerJobs {
    private final DroneService droneService;

    public BatterLevelCheckerJobs(DroneService droneService) {
        this.droneService = droneService;
    }


    @Scheduled(fixedRate = 10000)
    public void checkBatteryLevelAndLogStatus(){
        log.info("=============starting scheduled job to check for battery level==========");
        this.droneService.checkForDroneBatteryPercentAndLog();
    }
}
