package com.example.demo.service;

import com.example.demo.models.Drone;

import javax.xml.bind.ValidationException;

public interface DroneService {
    String registerDrone(Drone drone) throws ValidationException;
}
