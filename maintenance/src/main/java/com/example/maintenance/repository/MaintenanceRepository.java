package com.example.maintenance.repository;

import com.example.maintenance.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByTransformerId(Long transformerId);
}
