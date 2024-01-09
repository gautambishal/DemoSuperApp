package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Drone {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Size(min = 3, max = 100, message = "serial number should be between 3 to 100 characters")
    String serialNumber;
    String model;
    Integer weight;
    Integer batteryPercentage;
    State state;
}
enum State{
    IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
}
