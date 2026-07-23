package com.example.alert.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertDTO {
    @NotNull(message = "Transformer ID is required")
    private Long transformerId;

    @NotBlank(message = "Alert type is required")
    private String alertType;

    @NotBlank(message = "Priority is required")
    private String priority;

    @NotBlank(message = "Message is required")
    private String message;

    private String status;

    private LocalDateTime createdAt;
}
