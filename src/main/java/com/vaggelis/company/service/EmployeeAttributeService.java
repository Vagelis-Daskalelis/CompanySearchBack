package com.vaggelis.company.service;

import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.repository.AttributeRepository;
import com.vaggelis.company.repository.EmployeeRepository;
import com.vaggelis.company.service.exceptions.EntityAlreadyExistsException;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeAttributeService implements IEmployeeAttributeService{

    private final EmployeeRepository employeeRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;

    @Override
    public Employee addAttribute(Long employeeId, Long attributeId) throws Exception {
        Employee employee;
        Attribute attribute;
        List<Attribute> existing;
        Employee updated;

        try {
            employee = employeeRepository.findEmployeeById(employeeId);
            if (employee == null) throw new Exception("Not found");

            attribute = attributeRepository.findAttributeById(attributeId);
            if (attribute == null) throw new Exception("Not found");

//            existing = getAttributeFromEmployee(employeeId, attribute.getName());
//            if (attribute == existing) throw new Exception("Already Exists");

            existing = employee.getAttributes().stream()
                    .filter(attr -> attr.getName().equals(attribute.getName()))
                    .toList();

            if (!existing.isEmpty()) throw new Exception("Already Exists");

            employee.addAttribute(attribute);
            updated = employeeRepository.save(employee);

        }catch (Exception e){
            throw e;
        }
        return updated;
    }

    @Override
    public Employee removeAttribute(Long employeeId, Long attributeId) throws Exception {
        Employee employee;
        Attribute attribute;
        Employee updated;
        List<Employee> checked;

        try {
            employee = employeeRepository.findEmployeeById(employeeId);
            if (employee == null) throw new Exception("Not found");

            attribute = attributeRepository.findAttributeById(attributeId);
            if (attribute == null) throw new Exception("Not found");


            employee.removeAttribute(attribute);
            updated = employeeRepository.save(employee);

            checked = getEmployeesFromAttributes(attributeId);
            if (checked.isEmpty()) {
                attributeService.deleteAttribute(attributeId);
            }


        } catch (Exception e) {
            throw e;
        }
        return updated;
    }

    @Override
    public List<Employee> getEmployeesByAttributeValue(String value) throws Exception {
        return employeeRepository.findByAttributesValue(value);
    }

//    private Attribute getAttributeFromEmployee(Long employeeId, Long attributeId){
//        Employee employee;
//        Attribute attribute;
//
//
//        employee = employeeRepository.findEmployeeById(employeeId);
//
//        attribute = attributeRepository.findAttributeById(attributeId);
//
//
//        return attribute = (Attribute) employee.getAttributes().stream().filter(attributes -> attributes.getId().equals(attributeId));
//
//    }

    private Attribute getAttributeFromEmployee(Long employeeId, String name) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);

        return employee.getAttributes().stream()
                .filter(attr -> attr.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private List<Employee> getEmployeesFromAttributes(Long id){
        Attribute attribute = attributeRepository.findAttributeById(id);


        return attribute.getEmployees().stream().toList();
    }

}
