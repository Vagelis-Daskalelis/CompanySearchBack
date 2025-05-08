package com.vaggelis.company.service;

import com.vaggelis.company.DTO.attributeDTO.AttributeInsertDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeUpdateDTO;
import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;

import java.util.List;


public interface IAttributeService {
    Attribute insertAttribute(AttributeInsertDTO dto) throws Exception;
    Attribute updateAttribute(AttributeUpdateDTO dto) throws EntityNotFoundException;
    Attribute deleteAttribute(Long id) throws EntityNotFoundException;
    List<Attribute> findAllAttributes() throws Exception;
    Attribute findAttribute(Long id) throws EntityNotFoundException;
}
