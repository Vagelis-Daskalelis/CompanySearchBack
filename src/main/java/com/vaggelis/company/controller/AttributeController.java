package com.vaggelis.company.controller;

import com.vaggelis.company.DTO.attributeDTO.AttributeInsertDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeReadDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeUpdateDTO;
import com.vaggelis.company.mapper.Mapping;
import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.service.IAttributeService;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AttributeController {

    private final IAttributeService attributeService;

    @GetMapping("/attributes")
    public ResponseEntity<List<AttributeReadDTO>> getAllAttributes(){
        List<Attribute> attributes;

        try {
            attributes = attributeService.findAllAttributes();
            List<AttributeReadDTO> readDTOS = new ArrayList<>();

            for (Attribute attribute : attributes){
                readDTOS.add(Mapping.mappingFromAttribute(attribute));
            }
            return new ResponseEntity<>(readDTOS, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/attributes")
    public ResponseEntity<AttributeReadDTO> addAttribute(@RequestBody AttributeInsertDTO dto){
        try {
            Attribute attribute = attributeService.insertAttribute(dto);
            AttributeReadDTO attributeReadDTO = Mapping.mappingFromAttribute(attribute);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(attributeReadDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(attributeReadDTO);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/attributes/update")
    public ResponseEntity<AttributeReadDTO> updateAttribute(@RequestBody AttributeUpdateDTO dto){
        try {
            Attribute attribute = attributeService.updateAttribute(dto);
            AttributeReadDTO attributeReadDTO = Mapping.mappingFromAttribute(attribute);
            return new ResponseEntity<>(attributeReadDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/attributes/delete/{id}")
    public ResponseEntity<AttributeReadDTO> deleteAttribute(@PathVariable Long id){
        try {
            Attribute attribute = attributeService.deleteAttribute(id);
            AttributeReadDTO attributeReadDTO = Mapping.mappingFromAttribute(attribute);
            return ResponseEntity.ok(attributeReadDTO);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/attributes/{id}")
    public ResponseEntity<AttributeReadDTO> findAttribute(@PathVariable Long id){
        try {
            Attribute attribute = attributeService.findAttribute(id);
            AttributeReadDTO attributeReadDTO = Mapping.mappingFromAttribute(attribute);
            return new ResponseEntity<>(attributeReadDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
