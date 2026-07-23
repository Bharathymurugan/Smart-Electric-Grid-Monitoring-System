package com.example.maintenance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transformerId;

    private String maintenanceType;

    private LocalDate scheduledDate;

    private String engineerName;

    private String remarks;

    private String status;
}
