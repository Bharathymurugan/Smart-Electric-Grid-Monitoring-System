package com.example.sensor.service;

import com.example.sensor.dto.SensorDTO;

import java.util.List;

public interface SensorService {
    SensorDTO addSensorReading(SensorDTO sensorDTO);

    List<SensorDTO> getSensorReadingsByTransformerId(Long transformerId);
}
