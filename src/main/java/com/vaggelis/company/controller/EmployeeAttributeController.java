package com.vaggelis.company.controller;

import com.vaggelis.company.DTO.employeeDTO.EmployeeReadDTO;
import com.vaggelis.company.mapper.Mapping;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.service.EmployeeAttributeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EmployeeAttributeController {

    private final EmployeeAttributeServiceImpl employeeAttributeService;

    @PutMapping("/employee-attributes/update/{employeeId}/{attributeId}")
    public ResponseEntity<EmployeeReadDTO> addAttributeToEmployee(@PathVariable Long employeeId, @PathVariable Long attributeId) {
        try {
            Employee employee = employeeAttributeService.addAttribute(employeeId, attributeId);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            return new ResponseEntity<>(employeeReadDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee-attributes/delete/{employeeId}/{attributeId}")
    public ResponseEntity<EmployeeReadDTO> removeAttributeFromEmployee(@PathVariable Long employeeId, @PathVariable Long attributeId){
        try {
            Employee employee = employeeAttributeService.removeAttribute(employeeId, attributeId);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            return new ResponseEntity<>(employeeReadDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee-attributes/{value}")
    public ResponseEntity<List<EmployeeReadDTO>> getEmployeesByAttribute(@PathVariable String value){
        List<Employee> employees;

        try {
            employees = employeeAttributeService.getEmployeesByAttributeValue(value);
            List<EmployeeReadDTO> readDTOS = new ArrayList<>();

            for (Employee employee : employees){
                readDTOS.add(Mapping.mappingFromEmployee(employee));
            }
            return new ResponseEntity<>(readDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
