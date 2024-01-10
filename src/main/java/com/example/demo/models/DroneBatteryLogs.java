package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class DroneBatteryLogs {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name="log_id")
    Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String serialNumber;

    private Integer batteryPercentage;
}

