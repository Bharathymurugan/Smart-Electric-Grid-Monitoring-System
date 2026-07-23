package com.example.maintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransformerDTO {
    private Long id;

    private String serialNumber;

    private String transformerName;

    private String status;
}
