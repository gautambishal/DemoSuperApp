package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Drone {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name="drone_id")
    Integer id;
    @Size(min = 3, max = 100, message = "serial number should be between 3 to 100 characters")
    String serialNumber;
    String model;
    Integer weight;
    Integer batteryPercentage;
    State state;
}

