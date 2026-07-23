package com.example.alert.service;

import com.example.alert.dto.AlertDTO;
import com.example.alert.dto.MaintenanceDTO;
import com.example.alert.entity.Alert;
import com.example.alert.repository.AlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.alert.exception.AlertNotFoundException;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertServiceImpl implements AlertService{
    private static final Logger logger =
            LoggerFactory.getLogger(AlertServiceImpl.class);

    private final AlertRepository alertRepository;

    private final RestTemplate restTemplate;
    public AlertServiceImpl(AlertRepository alertRepository, RestTemplate restTemplate) {
        this.alertRepository = alertRepository;
        this.restTemplate=restTemplate;
    }

    @Override
    public AlertDTO createAlert(AlertDTO alertDTO) {

        logger.info("Creating alert for Transformer ID: {}",
                alertDTO.getTransformerId());

        Alert alert = new Alert();

        alert.setTransformerId(alertDTO.getTransformerId());
        alert.setAlertType(alertDTO.getAlertType());
        alert.setPriority(alertDTO.getPriority());
        alert.setMessage(alertDTO.getMessage());

        alert.setStatus("OPEN");
        alert.setCreatedAt(LocalDateTime.now());

        Alert savedAlert = alertRepository.save(alert);

        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();

        maintenanceDTO.setTransformerId(savedAlert.getTransformerId());
        maintenanceDTO.setMaintenanceType("CORRECTIVE");
        maintenanceDTO.setScheduledDate(LocalDate.now().plusDays(1));
        maintenanceDTO.setEngineerName("System");
        maintenanceDTO.setRemarks("Automatically scheduled due to HIGH priority alert.");
        maintenanceDTO.setStatus("SCHEDULED");
        restTemplate.postForObject(
                "http://localhost:8084/maintenance",
                maintenanceDTO,
                MaintenanceDTO.class
        );

        logger.info("Maintenance scheduled successfully for Transformer ID: {}",
                savedAlert.getTransformerId());

        AlertDTO response = new AlertDTO();

        response.setTransformerId(savedAlert.getTransformerId());
        response.setAlertType(savedAlert.getAlertType());
        response.setPriority(savedAlert.getPriority());
        response.setMessage(savedAlert.getMessage());
        response.setStatus(savedAlert.getStatus());
        response.setCreatedAt(savedAlert.getCreatedAt());

        logger.info("Alert created successfully.");

        return response;
    }

    @Override
    public List<AlertDTO> getAllAlerts() {

        logger.info("Fetching all alerts.");

        List<Alert> alerts = alertRepository.findAll();

        List<AlertDTO> dtoList = new ArrayList<>();

        for (Alert alert : alerts) {

            AlertDTO dto = new AlertDTO();

            dto.setTransformerId(alert.getTransformerId());
            dto.setAlertType(alert.getAlertType());
            dto.setPriority(alert.getPriority());
            dto.setMessage(alert.getMessage());
            dto.setStatus(alert.getStatus());
            dto.setCreatedAt(alert.getCreatedAt());

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public AlertDTO acknowledgeAlert(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alert not found"));

        alert.setStatus("ACKNOWLEDGED");

        Alert updatedAlert = alertRepository.save(alert);

        AlertDTO dto = new AlertDTO();

        dto.setTransformerId(updatedAlert.getTransformerId());
        dto.setAlertType(updatedAlert.getAlertType());
        dto.setPriority(updatedAlert.getPriority());
        dto.setMessage(updatedAlert.getMessage());
        dto.setStatus(updatedAlert.getStatus());
        dto.setCreatedAt(updatedAlert.getCreatedAt());

        logger.info("Alert {} acknowledged.", id);

        return dto;
    }

    @Override
    public AlertDTO resolveAlert(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alert not found"));

        alert.setStatus("RESOLVED");

        Alert updatedAlert = alertRepository.save(alert);

        AlertDTO dto = new AlertDTO();

        dto.setTransformerId(updatedAlert.getTransformerId());
        dto.setAlertType(updatedAlert.getAlertType());
        dto.setPriority(updatedAlert.getPriority());
        dto.setMessage(updatedAlert.getMessage());
        dto.setStatus(updatedAlert.getStatus());
        dto.setCreatedAt(updatedAlert.getCreatedAt());

        logger.info("Alert {} resolved.", id);

        return dto;
    }
}
