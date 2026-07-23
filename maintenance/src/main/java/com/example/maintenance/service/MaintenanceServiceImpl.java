package com.example.maintenance.service;

import com.example.maintenance.dto.MaintenanceDTO;
import com.example.maintenance.entity.Maintenance;
import com.example.maintenance.exception.MaintenanceNotFoundException;
import com.example.maintenance.repository.MaintenanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService{
    private static final Logger logger =
            LoggerFactory.getLogger(MaintenanceServiceImpl.class);

    private final MaintenanceRepository maintenanceRepository;
    private final RestTemplate restTemplate;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository,
                                  RestTemplate restTemplate) {
        this.maintenanceRepository = maintenanceRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    public MaintenanceDTO scheduleMaintenance(MaintenanceDTO maintenanceDTO) {

        logger.info("Scheduling maintenance for Transformer ID: {}",
                maintenanceDTO.getTransformerId());

        Maintenance maintenance = new Maintenance();

        maintenance.setTransformerId(maintenanceDTO.getTransformerId());
        maintenance.setMaintenanceType(maintenanceDTO.getMaintenanceType());
        maintenance.setScheduledDate(maintenanceDTO.getScheduledDate());
        maintenance.setEngineerName(maintenanceDTO.getEngineerName());
        maintenance.setRemarks(maintenanceDTO.getRemarks());
        maintenance.setStatus("SCHEDULED");

        Maintenance savedMaintenance =
                maintenanceRepository.save(maintenance);

        restTemplate.put(

                "http://localhost:8081/transformers/"
                        + savedMaintenance.getTransformerId()
                        + "/status?status=OUT_OF_SERVICE",

                null
        );
        logger.info("Maintenance record saved successfully.");

        return convertToDTO(savedMaintenance);
    }
    @Override
    public List<MaintenanceDTO> getAllMaintenance() {

        logger.info("Fetching all maintenance records.");

        List<Maintenance> maintenanceList = maintenanceRepository.findAll();

        List<MaintenanceDTO> dtoList = new ArrayList<>();

        for (Maintenance maintenance : maintenanceList) {
            dtoList.add(convertToDTO(maintenance));
        }

        return dtoList;
    }
    @Override
    public MaintenanceDTO getMaintenanceById(Long id) {

        logger.info("Fetching maintenance record with ID: {}", id);

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException("Maintenance record not found."));

        return convertToDTO(maintenance);
    }


    @Override
    public MaintenanceDTO updateMaintenance(Long id,
                                            MaintenanceDTO maintenanceDTO) {

        logger.info("Updating maintenance record: {}", id);

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException("Maintenance record not found."));

        maintenance.setTransformerId(maintenanceDTO.getTransformerId());
        maintenance.setMaintenanceType(maintenanceDTO.getMaintenanceType());
        maintenance.setScheduledDate(maintenanceDTO.getScheduledDate());
        maintenance.setEngineerName(maintenanceDTO.getEngineerName());
        maintenance.setRemarks(maintenanceDTO.getRemarks());
        maintenance.setStatus(maintenanceDTO.getStatus());

        Maintenance updated =
                maintenanceRepository.save(maintenance);

        logger.info("Maintenance updated successfully.");

        return convertToDTO(updated);
    }
    @Override
    public void deleteMaintenance(Long id) {

        logger.info("Deleting maintenance record: {}", id);

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException("Maintenance record not found."));

        maintenanceRepository.delete(maintenance);

        logger.info("Maintenance deleted successfully.");
    }

    @Override
    public MaintenanceDTO completeMaintenance(Long id) {

        logger.info("Completing maintenance for ID: {}", id);

        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException("Maintenance record not found."));

        maintenance.setStatus("COMPLETED");

        Maintenance updated =
                maintenanceRepository.save(maintenance);

        restTemplate.put(

                "http://localhost:8081/transformers/"
                        + updated.getTransformerId()
                        + "/status?status=ACTIVE",

                null
        );
        logger.info("Maintenance marked as COMPLETED.");

        return convertToDTO(updated);
    }


    private MaintenanceDTO convertToDTO(Maintenance maintenance) {

        MaintenanceDTO dto = new MaintenanceDTO();

        dto.setTransformerId(maintenance.getTransformerId());
        dto.setMaintenanceType(maintenance.getMaintenanceType());
        dto.setScheduledDate(maintenance.getScheduledDate());
        dto.setEngineerName(maintenance.getEngineerName());
        dto.setRemarks(maintenance.getRemarks());
        dto.setStatus(maintenance.getStatus());

        return dto;
    }
}
