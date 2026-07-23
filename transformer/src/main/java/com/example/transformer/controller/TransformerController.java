package com.example.transformer.controller;

import com.example.transformer.dto.TransformerDTO;
import com.example.transformer.service.TransformerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transformers")
public class TransformerController {
    private final TransformerService transformerService;

    public TransformerController(TransformerService transformerService){
        this.transformerService=transformerService;
    }
    @PostMapping
    public TransformerDTO registerTransformer(@Valid @RequestBody TransformerDTO transformerDTO){
        return transformerService.registerTransformer(transformerDTO);
    }
    @GetMapping
    public List<TransformerDTO> getAllTransformers(){
        return transformerService.getAllTransformers();
    }
    @GetMapping("/{id}")
    public TransformerDTO getTransformerById(@PathVariable Long id){
        return transformerService.getTransformerById(id);
    }
    @PutMapping("/{id}")
    public TransformerDTO updateTransformer(@PathVariable Long id,@Valid @RequestBody TransformerDTO transformerDTO){
        return transformerService.updateTransformer(id, transformerDTO);
    }
    @PutMapping("/{id}/status")
    public TransformerDTO updateStatus(
            @PathVariable Long id,
            @RequestParam String status){

        return transformerService.updateStatus(id,status);
    }
    @DeleteMapping("/{id}")
    public String deleteTransformer(@PathVariable Long id){
        transformerService.deleteTransformer(id);
        return "Transformer deleted successfully";
    }
}
