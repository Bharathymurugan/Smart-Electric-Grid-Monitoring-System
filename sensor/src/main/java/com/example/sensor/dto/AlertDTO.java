package com.example.sensor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertDTO {
    private Long transformerId;
    private String alertType;
    private String priority;
    private String message;
}
