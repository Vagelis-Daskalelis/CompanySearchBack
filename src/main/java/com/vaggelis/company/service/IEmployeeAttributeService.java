package com.vaggelis.company.service;

import com.vaggelis.company.model.Employee;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;

import java.util.List;


public interface IEmployeeAttributeService {
    Employee addAttribute(Long employeeId, Long attributeId)throws Exception;
    Employee removeAttribute(Long employeeId, Long attributeId) throws Exception;
    List<Employee> getEmployeesByAttributeValue(String value) throws Exception;
}
