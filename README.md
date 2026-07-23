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

# 🔗 Inter-Service Communication

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

# 🗄️ Database Design

Each microservice maintains its own independent database.

- transformer_db
- sensor_db
- alert_db
- maintenance_db

This follows the **Database per Microservice** architecture pattern.

---

# ▶️ Running the Project

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

