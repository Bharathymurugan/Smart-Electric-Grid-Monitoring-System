package com.example.alert.service;

import com.example.alert.dto.AlertDTO;

import java.util.List;

public interface AlertService {
    AlertDTO createAlert(AlertDTO alertDTO);

    List<AlertDTO> getAllAlerts();

    AlertDTO acknowledgeAlert(Long id);

    AlertDTO resolveAlert(Long id);
}
