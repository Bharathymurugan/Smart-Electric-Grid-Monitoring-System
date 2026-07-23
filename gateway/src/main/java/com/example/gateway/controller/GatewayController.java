package com.example.gateway.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final RestTemplate restTemplate;

    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/transformers")
    public String getAllTransformers() {

        return restTemplate.getForObject(
                "http://localhost:8081/transformers",
                String.class
        );
    }

    @GetMapping("/transformers/{id}")
    public String getTransformerById(@PathVariable Long id) {

        return restTemplate.getForObject(
                "http://localhost:8081/transformers/" + id,
                String.class
        );
    }

    @PostMapping("/transformers")
    public Object addTransformer(@RequestBody Object transformer) {

        return restTemplate.postForObject(
                "http://localhost:8081/transformers",
                transformer,
                Object.class
        );
    }

    @PutMapping("/transformers/{id}")
    public void updateTransformer(
            @PathVariable Long id,
            @RequestBody Object transformer) {

        restTemplate.put(
                "http://localhost:8081/transformers/" + id,
                transformer
        );
    }

    @PutMapping("/transformers/{id}/status")
    public void updateTransformerStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        restTemplate.put(
                "http://localhost:8081/transformers/"
                        + id
                        + "/status?status="
                        + status,
                null
        );
    }

    @DeleteMapping("/transformers/{id}")
    public void deleteTransformer(@PathVariable Long id) {

        restTemplate.delete(
                "http://localhost:8081/transformers/" + id
        );
    }

    @PostMapping("/sensors/readings")
    public Object addSensorReading(@RequestBody Object sensor) {

        return restTemplate.postForObject(
                "http://localhost:8082/sensors/readings",
                sensor,
                Object.class
        );
    }

    @GetMapping("/sensors/{transformerId}")
    public String getSensorReadings(
            @PathVariable Long transformerId) {

        return restTemplate.getForObject(
                "http://localhost:8082/sensors/" + transformerId,
                String.class
        );
    }


    @PostMapping("/alerts")
    public Object createAlert(@RequestBody Object alert) {

        return restTemplate.postForObject(
                "http://localhost:8083/alerts",
                alert,
                Object.class
        );
    }

    @GetMapping("/alerts")
    public String getAllAlerts() {

        return restTemplate.getForObject(
                "http://localhost:8083/alerts",
                String.class
        );
    }

    @GetMapping("/alerts/{id}")
    public String getAlertById(@PathVariable Long id) {

        return restTemplate.getForObject(
                "http://localhost:8083/alerts/" + id,
                String.class
        );
    }
    @PutMapping("/alerts/{id}/acknowledge")
    public void acknowledgeAlert(@PathVariable Long id) {

        restTemplate.put(
                "http://localhost:8083/alerts/" + id + "/acknowledge",
                null
        );
    }
    @PutMapping("/alerts/{id}/resolve")
    public void resolveAlert(@PathVariable Long id) {

        restTemplate.put(
                "http://localhost:8083/alerts/" + id + "/resolve",
                null
        );
    }

    @PostMapping("/maintenance")
    public Object scheduleMaintenance(
            @RequestBody Object maintenance) {

        return restTemplate.postForObject(
                "http://localhost:8084/maintenance",
                maintenance,
                Object.class
        );
    }

    @GetMapping("/maintenance")
    public String getAllMaintenance() {

        return restTemplate.getForObject(
                "http://localhost:8084/maintenance",
                String.class
        );
    }

    @GetMapping("/maintenance/{id}")
    public String getMaintenanceById(
            @PathVariable Long id) {

        return restTemplate.getForObject(
                "http://localhost:8084/maintenance/" + id,
                String.class
        );
    }

    @PutMapping("/maintenance/{id}")
    public void updateMaintenance(
            @PathVariable Long id,
            @RequestBody Object maintenance) {

        restTemplate.put(
                "http://localhost:8084/maintenance/" + id,
                maintenance
        );
    }

    @PutMapping("/maintenance/{id}/complete")
    public void completeMaintenance(
            @PathVariable Long id) {

        restTemplate.put(
                "http://localhost:8084/maintenance/" + id + "/complete",
                null
        );
    }

    @DeleteMapping("/maintenance/{id}")
    public void deleteMaintenance(
            @PathVariable Long id) {

        restTemplate.delete(
                "http://localhost:8084/maintenance/" + id
        );
    }

}