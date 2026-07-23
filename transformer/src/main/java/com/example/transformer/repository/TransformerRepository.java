package com.example.transformer.repository;

import com.example.transformer.entity.Transformer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransformerRepository extends JpaRepository<Transformer, Long> {

    public Optional<Transformer> findBySerialNumber(String serialNumber);
    boolean existsBySerialNumber(String serialNumber);
}
