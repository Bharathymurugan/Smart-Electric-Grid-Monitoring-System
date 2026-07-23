package com.example.transformer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerDTO {
    @NotBlank(message= "Serial Number is required")
    private String serialNumber;
    @NotBlank(message="Transformer name is required")
    private String transformerName;
    @Positive(message="Capacity must be greater than 0")
    private double capacity;
    @NotBlank(message = "Location is required")
    private String location;
    @NotBlank(message = "Status is required")
    private String status;
}
