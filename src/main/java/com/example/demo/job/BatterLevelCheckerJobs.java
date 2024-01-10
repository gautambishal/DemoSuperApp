package com.example.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatterLevelCheckerJobs {

    @Scheduled(fixedRateString = "3000")
    public void checkBatteryLevelAndLogStatus(){

    }
}
