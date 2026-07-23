package com.example.transformer.service;

import com.example.transformer.dto.TransformerDTO;

import java.util.List;

public interface TransformerService {
    TransformerDTO registerTransformer(TransformerDTO transformerDTO);
    List<TransformerDTO> getAllTransformers();
    TransformerDTO getTransformerById(Long id);
    TransformerDTO updateTransformer(Long id, TransformerDTO transformerDTO);
    TransformerDTO updateStatus(Long id,String status);
    void deleteTransformer(Long id);
}
