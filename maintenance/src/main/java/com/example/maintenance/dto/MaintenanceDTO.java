package com.example.maintenance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MaintenanceDTO {
    private Long transformerId;

    private String maintenanceType;

    private LocalDate scheduledDate;

    private String engineerName;

    private String remarks;

    private String status;
}
