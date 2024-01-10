package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name = "medication_id")
    Integer id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "Name can only contain letters,numbers and (-,_) ")
    @JsonProperty("name")
    String name;
    @JsonProperty("weight")
    Integer weight;
    @Pattern(regexp = "^[A-Z0-9_-]+$",message = "Code can only contain upper case letters, underscore and numbers")
    @JsonProperty("code")
    String code;
    String path;

    public Medication(String name, Integer weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }
}
