package com.vaggelis.company.service;

import com.vaggelis.company.DTO.employeeDTO.EmployeeInsertDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeUpdateDTO;
import com.vaggelis.company.mapper.Mapping;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.repository.EmployeeRepository;
import com.vaggelis.company.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee insertEmployee(EmployeeInsertDTO dto) throws Exception {
        Employee employee;



        try {
            employee = employeeRepository.save(Mapping.mappingFromInsert(dto));
            if (employee.getId() == null) throw new Exception("Insert error");
        }catch (Exception e){
            throw e;
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateDTO dto) throws EntityNotFoundException {
        Employee employee;
        Employee updatedEmployee;

        try {
            employee = employeeRepository.findEmployeeById(dto.getId());
            if (employee == null) throw new EntityNotFoundException(Employee.class, dto.getId());

            updatedEmployee = employeeRepository.save(Mapping.mappingFromUpdate(dto));
        }catch (EntityNotFoundException e){
            throw e;
        }
        return updatedEmployee;
    }

    @Override
    public Employee deleteEmployee(Long id) throws EntityNotFoundException {
        Employee employee = null;

        try {
            employee = employeeRepository.findEmployeeById(id);
            if (employee == null) throw new EntityNotFoundException(Employee.class, id);
            employeeRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw e;
        }
        return employee;
    }

    @Override
    public List<Employee> findAllEmployees() throws Exception {
        return employeeRepository.findAll();
    }

    @Override
    public List<String> findAllByAddresses() throws Exception{
        List<Employee> employees = employeeRepository.findAll();

        List<String> addresses = employees.stream().map(Employee::getAddress).toList();
        return addresses;
    }

    @Override
    public Employee findByAddress(String address) throws EntityNotFoundException {
        Employee employee;

        try {
            employee = employeeRepository.findByAddress(address);
            if (employee == null) throw new EntityNotFoundException(Employee.class, employee.getId());
        }catch (EntityNotFoundException e){
            throw e;
        }
        return employee;
    }

    @Override
    public Employee findEmployee(Long id) throws EntityNotFoundException {
        Employee employee;

        try {
            employee = employeeRepository.findEmployeeById(id);
            if (employee == null) throw new EntityNotFoundException(Employee.class, id);
        }catch (EntityNotFoundException e){
            throw e;
        }
        return employee;
    }
}
