package com.example.sensor.controller;

import com.example.sensor.dto.SensorDTO;
import com.example.sensor.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/readings")
    public SensorDTO addSensorReading(@Valid @RequestBody SensorDTO sensorDTO) {
        return sensorService.addSensorReading(sensorDTO);
    }

    @GetMapping("/{transformerId}")
    public List<SensorDTO> getSensorReadingsByTransformerId(
            @PathVariable Long transformerId) {

        return sensorService.getSensorReadingsByTransformerId(transformerId);
    }
}
