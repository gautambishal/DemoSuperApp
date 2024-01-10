package com.example.demo.controller;

import com.example.demo.models.Drone;
import com.example.demo.models.State;
import com.example.demo.repo.DroneRepository;
import com.example.demo.service.DroneService;
import com.example.demo.validator.DTOValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@WebMvcTest(DroneController.class)
public class DroneControllerTest {

    @MockBean
    DroneService droneService;

    @MockBean
    DTOValidator dtoValidator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPingApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Alive!!"));
    }

    @Test
    public void testRegisterDrone() throws Exception {
        Drone drone = new Drone();
        when(droneService.registerDrone(any(Drone.class))).thenReturn("Drone registered successfully");

        mockMvc.perform(MockMvcRequestBuilders.post("/registerDrone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Drone registered successfully"));
    }

    @Test
    public void testGetDroneBatteryPercentage() throws Exception {
        String serialNumber = "DR123";
        when(droneService.getDroneBatteryPercentage(serialNumber)).thenReturn("75");

        mockMvc.perform(MockMvcRequestBuilders.get("/getBatteryPercentage/{serialNo}", serialNumber))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("75"));
    }

    @Test
    public void testGetAvailableDrones() throws Exception {
        Drone drone = Drone.builder()
                .serialNumber("DR123").state(State.IDLE).model("Middleweight").weight(400)
                .build();
        when(droneService.getAvailableDrones()).thenReturn(Collections.singletonList(drone));

        mockMvc.perform(MockMvcRequestBuilders.get("/getAvailableDrones"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].serialNumber").value("DR123"));
    }
}
