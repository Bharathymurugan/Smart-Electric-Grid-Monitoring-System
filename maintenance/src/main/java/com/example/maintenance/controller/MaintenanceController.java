package com.example.maintenance.controller;

import com.example.maintenance.dto.MaintenanceDTO;
import com.example.maintenance.service.MaintenanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public MaintenanceDTO scheduleMaintenance(
            @RequestBody MaintenanceDTO maintenanceDTO) {

        return maintenanceService.scheduleMaintenance(maintenanceDTO);
    }

    @GetMapping
    public List<MaintenanceDTO> getAllMaintenance() {

        return maintenanceService.getAllMaintenance();
    }

    @GetMapping("/{id}")
    public MaintenanceDTO getMaintenanceById(
            @PathVariable Long id) {

        return maintenanceService.getMaintenanceById(id);
    }

    @PutMapping("/{id}")
    public MaintenanceDTO updateMaintenance(
            @PathVariable Long id,
            @RequestBody MaintenanceDTO maintenanceDTO) {

        return maintenanceService.updateMaintenance(id, maintenanceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(
            @PathVariable Long id) {

        maintenanceService.deleteMaintenance(id);
    }

    @PutMapping("/{id}/complete")
    public MaintenanceDTO completeMaintenance(
            @PathVariable Long id) {

        return maintenanceService.completeMaintenance(id);
    }
}
