package com.vaggelis.company.service;

import com.vaggelis.company.DTO.attributeDTO.AttributeInsertDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeUpdateDTO;
import com.vaggelis.company.mapper.Mapping;
import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.repository.AttributeRepository;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttributeService implements IAttributeService{

    private final AttributeRepository attributeRepository;

    @Override
    public Attribute insertAttribute(AttributeInsertDTO dto) throws Exception {
        Attribute attribute;

        try {

            Optional<Attribute> returnedAttribute = Optional.ofNullable(attributeRepository.findByValue(dto.getValue()));
            if (returnedAttribute.isPresent()) throw new Exception("Already exists");

            attribute = attributeRepository.save(Mapping.mappingFromInsert(dto));
            if (attribute.getId() == null) throw new Exception("Insert error");
        }catch (Exception e){
            throw e;
        }
        return attribute;
    }

    @Override
    public Attribute updateAttribute(AttributeUpdateDTO dto) throws EntityNotFoundException {
        Attribute attribute;
        Attribute updatedAttribute;

        try {
            attribute = attributeRepository.findAttributeById(dto.getId());
            if (attribute == null) throw new EntityNotFoundException(Attribute.class, dto.getId());

            updatedAttribute = attributeRepository.save(Mapping.mappingFromUpdate(dto));
        }catch (EntityNotFoundException e){
            throw e;
        }
        return updatedAttribute;
    }

    @Override
    public Attribute deleteAttribute(Long id) throws EntityNotFoundException {
        Attribute attribute = null;

        try {
            attribute = attributeRepository.findAttributeById(id);
            if (attribute == null) throw new EntityNotFoundException(Attribute.class, id);
            attributeRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw e;
        }
        return attribute;
    }

    @Override
    public List<Attribute> findAllAttributes() throws Exception {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute findAttribute(Long id) throws EntityNotFoundException {
        Attribute attribute;

        try {
            attribute = attributeRepository.findAttributeById(id);
            if (attribute == null) throw new EntityNotFoundException(Attribute.class, id);
        }catch (EntityNotFoundException e){
            throw e;
        }
        return attribute;
    }
}
