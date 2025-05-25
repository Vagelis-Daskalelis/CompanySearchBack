package com.vaggelis.company.service;

import com.vaggelis.company.DTO.employeeDTO.EmployeeInsertDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeUpdateDTO;
import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.service.exceptions.EntityAlreadyExistsException;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IEmployeeService {
    Employee insertEmployee(EmployeeInsertDTO dto) throws Exception;
    Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException;
    Employee deleteEmployee(Long id)throws EntityNotFoundException;
    List<Employee> findAllEmployees() throws Exception;

    List<String> findAllByAddresses() throws Exception;

    Employee findByAddress(String address) throws EntityNotFoundException;

    Employee findEmployee(Long id) throws EntityNotFoundException;

    List<Attribute> findAttributesByEmployee(Long id) throws EntityNotFoundException;


    List<String> findAddressesByAttribute(String value);


}
