package com.example.sensor.service;

import com.example.sensor.dto.AlertDTO;
import com.example.sensor.dto.SensorDTO;
import com.example.sensor.dto.TransformerDTO;
import com.example.sensor.entity.Sensor;
//import com.example.sensor.exception.SensorDataException;
import com.example.sensor.exception.SensorDataException;
import com.example.sensor.repository.SensorRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;
//import org.slf4j.LoggerFactory;

@Service

public class SensorServiceImpl implements SensorService{
    private final RestTemplate restTemplate;
    private static final Logger logger =
            LoggerFactory.getLogger(SensorServiceImpl.class);

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository, RestTemplate restTemplate) {
        this.sensorRepository = sensorRepository;
        this.restTemplate=restTemplate;
    }

    @Override
    public SensorDTO addSensorReading(SensorDTO sensorDTO) {

        try {

            TransformerDTO transformer =
                    restTemplate.getForObject(
                            "http://localhost:8081/transformers/"
                                    + sensorDTO.getTransformerId(),
                            TransformerDTO.class
                    );

            if ("OUT_OF_SERVICE".equals(transformer.getStatus())) {
                throw new SensorDataException(
                        "Transformer is currently OUT_OF_SERVICE."
                );
            }

        } catch (Exception e) {

            throw new SensorDataException("Transformer not found.");
        }
        logger.info("Adding sensor reading for Transformer ID: {}",
                sensorDTO.getTransformerId());


        Sensor sensor = new Sensor();

        sensor.setTransformerId(sensorDTO.getTransformerId());
        sensor.setVoltage(sensorDTO.getVoltage());
        sensor.setCurrent(sensorDTO.getCurrent());
        sensor.setTemperature(sensorDTO.getTemperature());
        sensor.setLoadValue(sensorDTO.getLoadValue());

        sensor.setTimestamp(LocalDateTime.now());

        Sensor savedSensor = sensorRepository.save(sensor);

        AlertDTO alertDTO=new AlertDTO();
        alertDTO.setTransformerId(savedSensor.getTransformerId());
        if (savedSensor.getTemperature() > 200) {

            alertDTO.setAlertType("OVERHEATING");
            alertDTO.setPriority("HIGH");
            alertDTO.setMessage("Transformer temperature exceeded safe limit.");

            restTemplate.postForObject(
                    "http://localhost:8083/alerts",
                    alertDTO,
                    AlertDTO.class
            );
            logger.info("Alert sent to Alert Service.");
        }
        else if (savedSensor.getLoadValue() > 150) {

            alertDTO.setAlertType("OVERLOAD");
            alertDTO.setPriority("HIGH");
            alertDTO.setMessage("Transformer load exceeded safe limit.");

            restTemplate.postForObject(
                    "http://localhost:8083/alerts",
                    alertDTO,
                    AlertDTO.class
            );
            logger.info("Alert sent to Alert Service.");
        }

        SensorDTO response = new SensorDTO();

        response.setTransformerId(savedSensor.getTransformerId());
        response.setVoltage(savedSensor.getVoltage());
        response.setCurrent(savedSensor.getCurrent());
        response.setTemperature(savedSensor.getTemperature());
        response.setLoadValue(savedSensor.getLoadValue());
        response.setTimestamp(savedSensor.getTimestamp());

        logger.info("Sensor reading saved successfully.");


        return response;
    }

    @Override
    public List<SensorDTO> getSensorReadingsByTransformerId(Long transformerId) {

        logger.info("Fetching sensor readings for Transformer ID: {}",
                transformerId);

        List<Sensor> sensors =
                sensorRepository.findByTransformerId(transformerId);

        List<SensorDTO> dtoList = new ArrayList<>();

        for (Sensor sensor : sensors) {

            SensorDTO dto = new SensorDTO();

            dto.setTransformerId(sensor.getTransformerId());
            dto.setVoltage(sensor.getVoltage());
            dto.setCurrent(sensor.getCurrent());
            dto.setTemperature(sensor.getTemperature());
            dto.setLoadValue(sensor.getLoadValue());
            dto.setTimestamp(sensor.getTimestamp());

            dtoList.add(dto);
        }

        return dtoList;
    }

}
