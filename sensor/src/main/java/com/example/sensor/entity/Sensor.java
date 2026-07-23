package com.example.sensor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name="sensor_readings")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transformerId;

    private double voltage;

    private double current;

    private double temperature;

    private double loadValue;

    private LocalDateTime timestamp;
}
