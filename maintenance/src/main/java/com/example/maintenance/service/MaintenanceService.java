package com.example.maintenance.service;

import com.example.maintenance.dto.MaintenanceDTO;

import java.util.List;

public interface MaintenanceService {
    MaintenanceDTO scheduleMaintenance(MaintenanceDTO maintenanceDTO);

    List<MaintenanceDTO> getAllMaintenance();

    MaintenanceDTO getMaintenanceById(Long id);

    MaintenanceDTO updateMaintenance(Long id, MaintenanceDTO maintenanceDTO);

    void deleteMaintenance(Long id);

    MaintenanceDTO completeMaintenance(Long id);
}
