package com.example.alert.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name= "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transformerId;

    private String alertType;

    private String priority;

    private String message;

    private String status;

    private LocalDateTime createdAt;
}
