package com.example.transformer.service;

import com.example.transformer.dto.TransformerDTO;
import com.example.transformer.entity.Transformer;
import com.example.transformer.repository.TransformerRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.example.transformer.exception.DuplicateSerialNumberException;
import com.example.transformer.exception.TransformerNotFoundException;
@Service
public class TransformerServiceImpl implements TransformerService {
    private final TransformerRepository transformerRepository;
    public TransformerServiceImpl(TransformerRepository transformerRepository){
        this.transformerRepository=transformerRepository;
    }
    @Override
    public TransformerDTO registerTransformer(TransformerDTO transformerDTO){
        if(transformerRepository.existsBySerialNumber(transformerDTO.getSerialNumber())){
            throw new DuplicateSerialNumberException("Transformer with this serial number already exists.");
        }
        Transformer transformer = new Transformer();
        transformer.setSerialNumber(transformerDTO.getSerialNumber());
        transformer.setTransformerName(transformerDTO.getTransformerName());
        transformer.setCapacity(transformerDTO.getCapacity());
        transformer.setLocation(transformerDTO.getLocation());
        transformer.setStatus(transformerDTO.getStatus());

        Transformer savedTransformer = transformerRepository.save(transformer);

        TransformerDTO response = new TransformerDTO();
        response.setSerialNumber(savedTransformer.getSerialNumber());
        response.setTransformerName(savedTransformer.getTransformerName());
        response.setCapacity(savedTransformer.getCapacity());
        response.setLocation(savedTransformer.getLocation());
        response.setStatus(savedTransformer.getStatus());

        return response;

    }
    @Override
    public List<TransformerDTO> getAllTransformers() {

        List<Transformer> transformers = transformerRepository.findAll();

        List<TransformerDTO> transformerDTOList = new ArrayList<>();

        for (Transformer transformer : transformers) {

            TransformerDTO dto = new TransformerDTO();

            dto.setSerialNumber(transformer.getSerialNumber());
            dto.setTransformerName(transformer.getTransformerName());
            dto.setCapacity(transformer.getCapacity());
            dto.setLocation(transformer.getLocation());
            dto.setStatus(transformer.getStatus());

            transformerDTOList.add(dto);
        }

        return transformerDTOList;
    }
    @Override
    public TransformerDTO getTransformerById(Long id) {

        Transformer transformer = transformerRepository.findById(id)
                .orElseThrow(() -> new TransformerNotFoundException("Transformer not found"));

        TransformerDTO dto = new TransformerDTO();

        dto.setSerialNumber(transformer.getSerialNumber());
        dto.setTransformerName(transformer.getTransformerName());
        dto.setCapacity(transformer.getCapacity());
        dto.setLocation(transformer.getLocation());
        dto.setStatus(transformer.getStatus());

        return dto;
    }
    @Override
    public TransformerDTO updateTransformer(Long id, TransformerDTO transformerDTO) {

        Transformer transformer = transformerRepository.findById(id)
                .orElseThrow(() -> new TransformerNotFoundException("Transformer not found"));

        transformer.setSerialNumber(transformerDTO.getSerialNumber());
        transformer.setTransformerName(transformerDTO.getTransformerName());
        transformer.setCapacity(transformerDTO.getCapacity());
        transformer.setLocation(transformerDTO.getLocation());
        transformer.setStatus(transformerDTO.getStatus());

        Transformer updatedTransformer = transformerRepository.save(transformer);

        TransformerDTO dto = new TransformerDTO();

        dto.setSerialNumber(updatedTransformer.getSerialNumber());
        dto.setTransformerName(updatedTransformer.getTransformerName());
        dto.setCapacity(updatedTransformer.getCapacity());
        dto.setLocation(updatedTransformer.getLocation());
        dto.setStatus(updatedTransformer.getStatus());

        return dto;
    }
    @Override
    public TransformerDTO updateStatus(Long id,
                                       String status){

        Transformer transformer =
                transformerRepository.findById(id)
                        .orElseThrow(()->
                                new RuntimeException("Transformer not found"));

        transformer.setStatus(status);

        Transformer updated =
                transformerRepository.save(transformer);

        TransformerDTO dto = new TransformerDTO();

        dto.setSerialNumber(updated.getSerialNumber());
        dto.setTransformerName(updated.getTransformerName());
        dto.setCapacity(updated.getCapacity());
        dto.setLocation(updated.getLocation());
        dto.setStatus(updated.getStatus());

        return dto;
    }
    @Override
    public void deleteTransformer(Long id) {

        Transformer transformer = transformerRepository.findById(id)
                .orElseThrow(() -> new TransformerNotFoundException("Transformer not found"));

        transformerRepository.delete(transformer);
    }

}
