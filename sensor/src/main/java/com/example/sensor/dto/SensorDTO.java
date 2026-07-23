package com.example.sensor.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorDTO {
    @NotNull(message = "Transformer ID is required")
    private Long transformerId;

    @Positive(message = "Voltage must be greater than 0")
    private double voltage;

    @Positive(message = "Current must be greater than 0")
    private double current;

    @DecimalMin(value = "-40.0", message = "Temperature cannot be below -40°C")
    @DecimalMax(value = "1000.0", message = "Temperature cannot exceed 200°C")
    private double temperature;

    @Positive(message = "Load must be greater than 0")
    private double loadValue;

    private LocalDateTime timestamp;
}
