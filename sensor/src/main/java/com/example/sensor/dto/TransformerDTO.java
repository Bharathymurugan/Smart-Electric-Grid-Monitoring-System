package com.example.sensor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerDTO {
    private String serialNumber;
    private String transformerName;
    private double capacity;
    private String location;
    private String status;
}
