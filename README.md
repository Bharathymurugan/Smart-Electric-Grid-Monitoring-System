# Smart Electric Grid Monitoring System

A **Spring Boot Microservices-based Smart Electric Grid Monitoring System** designed to monitor power transformers, collect real-time sensor readings, generate alerts, schedule maintenance activities, and manage transformer operations through REST APIs.

The project demonstrates a distributed microservices architecture using **Spring Boot, Spring Data JPA, MySQL, RestTemplate, and Spring Cloud Gateway**.

---

# Project Overview

The Smart Electric Grid Monitoring System continuously monitors electrical transformers by collecting sensor readings such as **voltage, current, temperature, and load**.

Based on the collected sensor data, the system automatically:

- Registers and manages transformers
- Validates transformer existence before storing sensor readings
- Detects abnormal operating conditions
- Generates high-priority alerts
- Schedules maintenance operations
- Updates transformer status during maintenance
- Completes maintenance and restores transformer status
- Routes all client requests through an API Gateway

---

# System Architecture

```
                          Client
                             │
                             ▼
                    API Gateway (8080)
                             │
 ┌───────────────────────────┼───────────────────────────┐
 ▼                           ▼                           ▼                          ▼
Transformer Service     Sensor Service             Alert Service          Maintenance Service
      8081                  8082                      8083                     8084
             └──────────── RestTemplate Communication ─────────────┘
```

---

# Project Workflow

```
Register Transformer
        │
        ▼
Record Sensor Reading
        │
        ▼
Validate Transformer
        │
        ▼
Store Reading
        │
        ▼
Threshold Check
        │
        ▼
Generate Alert
        │
        ▼
Schedule Maintenance
        │
        ▼
Update Transformer Status
        │
        ▼
Complete Maintenance
        │
        ▼
Restore Transformer Status
```

---

# Technology Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- RestTemplate
- Bean Validation
- SLF4J Logging
- Global Exception Handling
- Lombok
- Postman

---

# Microservices

## 1. Transformer Service

### Responsibilities

- Register transformers
- Update transformer information
- Delete transformers
- Retrieve transformer details
- Maintain transformer status (ACTIVE / OUT_OF_SERVICE)
- Prevent duplicate serial numbers

### Endpoints

```
POST   /transformers
GET    /transformers
GET    /transformers/{id}
PUT    /transformers/{id}
PUT    /transformers/{id}/status
DELETE /transformers/{id}
```

---

## 2. Sensor Service

### Responsibilities

- Record transformer sensor readings
- Validate transformer existence
- Monitor voltage
- Monitor current
- Monitor temperature
- Monitor load
- Generate alerts automatically

### Endpoints

```
POST /sensors/readings
GET  /sensors/{transformerId}
```

---

## 3. Alert Service

### Responsibilities

- Create alerts
- Retrieve alerts
- Acknowledge alerts
- Resolve alerts
- Manage alert priority and status

### Endpoints

```
POST /alerts
GET  /alerts
GET  /alerts/{id}
PUT  /alerts/{id}/acknowledge
PUT  /alerts/{id}/resolve
```

---

## 4. Maintenance Service

### Responsibilities

- Schedule maintenance
- Retrieve maintenance records
- Update maintenance details
- Complete maintenance
- Update transformer status after completion

### Endpoints

```
POST   /maintenance
GET    /maintenance
GET    /maintenance/{id}
PUT    /maintenance/{id}
PUT    /maintenance/{id}/complete
DELETE /maintenance/{id}
```

---

## 5. API Gateway

## API Gateway

The API Gateway acts as the centralized entry point for client requests. It forwards incoming HTTP requests to the appropriate microservice using `RestTemplate`.

### Gateway Endpoints

```
GET    /gateway/transformers
POST   /gateway/transformers

POST   /gateway/sensors/readings
GET    /gateway/sensors/{transformerId}

GET    /gateway/alerts
POST   /gateway/alerts
GET    /gateway/alerts/{id}
PUT    /gateway/alerts/{id}/acknowledge
PUT    /gateway/alerts/{id}/resolve

GET    /gateway/maintenance
POST   /gateway/maintenance
GET    /gateway/maintenance/{id}
PUT    /gateway/maintenance/{id}
PUT    /gateway/maintenance/{id}/complete
DELETE /gateway/maintenance/{id}
```

The gateway simplifies client interaction by providing a single entry point instead of requiring clients to communicate directly with individual microservices.
---

# Inter-Service Communication

The project uses **RestTemplate** for communication between microservices.

### Implemented Communication

### 1. Sensor → Transformer

- Validates transformer existence before storing readings.

### 2. Sensor → Alert

- Automatically generates HIGH priority alerts when thresholds are exceeded.

### 3. Maintenance → Transformer

- Marks transformer as **OUT_OF_SERVICE** when maintenance is scheduled.
- Restores transformer to **ACTIVE** when maintenance is completed.

---

# Alert Generation Rules

| Condition | Alert Type | Priority |
|-----------|------------|----------|
| Temperature > 200°C | OVERHEATING | HIGH |
| Load > 150 | OVERLOAD | HIGH |

---

# Project Structure

```
Smart-Electric-Grid-Monitoring-System
│
├── transformer-service
│
├── sensor-service
│
├── alert-service
│
├── maintenance-service
│
├── api-gateway
│
└── README.md
```

---

# Database Design

Each microservice maintains its own independent database.

- transformer_db
- sensor_db
- alert_db
- maintenance_db

This follows the **Database per Microservice** architecture pattern.

---

# Running the Project

Start the services in the following order:

1. Transformer Service (8081)
2. Sensor Service (8082)
3. Alert Service (8083)
4. Maintenance Service (8084)
5. API Gateway (8080)

Access APIs through:

```
http://localhost:8080
```

Example:

```
GET http://localhost:8080/gateway/transformers
```

---

# Testing

The APIs were tested using **Postman**.

### Tested Workflows

- Register Transformer
- Add Sensor Reading
- Automatic Alert Generation
- Schedule Maintenance
- Update Transformer Status
- Complete Maintenance
- Retrieve Sensor Readings
- CRUD Operations through API Gateway

---

# Features Implemented

- Spring Boot Microservices
- REST APIs
- CRUD Operations
- DTO Pattern
- Layered Architecture
- Bean Validation
- Global Exception Handling
- SLF4J Logging
- Spring Data JPA
- Hibernate ORM
- MySQL Integration
- RestTemplate Communication
- API Gateway
- Automatic Alert Generation
- Automatic Transformer Status Updates
- Maintenance Scheduling
- Postman API Testing

# OUTPUT
<img width="1920" height="1080" alt="Screenshot 2026-07-23 202639" src="https://github.com/user-attachments/assets/a35a435b-23e3-4991-8116-1a0786959601" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 202908" src="https://github.com/user-attachments/assets/e77edfdf-b123-4e9b-a22c-b403d2b4bb65" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 202943" src="https://github.com/user-attachments/assets/21e381ef-9de0-4fc7-8e35-516524391b9e" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 203134" src="https://github.com/user-attachments/assets/24659dd1-4a92-4e25-aa6d-573766ef1234" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 203516" src="https://github.com/user-attachments/assets/94d507ad-da8b-4b4f-a51f-014c85ca0bf7" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 203532" src="https://github.com/user-attachments/assets/e7a3ce1d-5142-4518-af45-c06cc9e162d7" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 203659" src="https://github.com/user-attachments/assets/ee575267-9315-4b8b-b4a9-a0b576ef5602" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 204740" src="https://github.com/user-attachments/assets/2df6742f-ccfa-4375-bb82-11618413752e" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 210134" src="https://github.com/user-attachments/assets/618fb99e-5f92-4a2a-9244-99879fc25eb4" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205235" src="https://github.com/user-attachments/assets/c07c6da6-c771-4a3e-a551-1472265d42b2" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205410" src="https://github.com/user-attachments/assets/6d8996df-4850-47cb-9f7d-cd3530f365ed" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205530" src="https://github.com/user-attachments/assets/c35f79d2-adce-45ab-9d68-243eb5f05af1" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205621" src="https://github.com/user-attachments/assets/bff3e551-3664-48ef-8b9e-e868bbebec7c" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205745" src="https://github.com/user-attachments/assets/21773b04-a4af-400d-9f59-4a406507c217" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205912" src="https://github.com/user-attachments/assets/7a3f23ce-c009-4f54-beba-4393fc277a4c" />

<img width="1920" height="1080" alt="Screenshot 2026-07-23 205941" src="https://github.com/user-attachments/assets/817a5f35-42b2-40d7-82d9-c0438aa64b30" />


