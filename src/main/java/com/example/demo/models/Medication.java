package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "Name can only contain letters,numbers and (-,_) ")
    String name;
    Integer weight;
    @Pattern(regexp = "^[A-Z0-9_-]+$",message = "Code can only contain upper case letters, underscore and numbers")
    String code;
    String path;
}
