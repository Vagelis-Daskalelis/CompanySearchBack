package com.vaggelis.company.controller;

import com.vaggelis.company.DTO.employeeDTO.EmployeeInsertDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeReadDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeUpdateDTO;
import com.vaggelis.company.mapper.Mapping;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.service.IEmployeeService;
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
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeReadDTO>> getAllEmployees(){
        List<Employee> employees;

        try {
            employees = employeeService.findAllEmployees();
            List<EmployeeReadDTO> readDTOS = new ArrayList<>();

            for (Employee employee : employees){
                readDTOS.add(Mapping.mappingFromEmployee(employee));
            }
            return new ResponseEntity<>(readDTOS, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/addresses")
    public ResponseEntity<List<String>> findAllAddresses(){
        List<String> addresses;

        try {
            addresses = employeeService.findAllByAddresses();
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeReadDTO> addEmployee(@RequestBody EmployeeInsertDTO dto){
        try {
            Employee employee = employeeService.insertEmployee(dto);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(employeeReadDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(employeeReadDTO);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/employees/update")
    public ResponseEntity<EmployeeReadDTO> updateEmployee(@RequestBody EmployeeUpdateDTO dto){
        try {
            Employee employee = employeeService.updateEmployee(dto);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            return new ResponseEntity<>(employeeReadDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employees/delete/{id}")
    public ResponseEntity<EmployeeReadDTO> deleteEmployee(@PathVariable Long id){
        try {
            Employee employee = employeeService.deleteEmployee(id);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            return ResponseEntity.ok(employeeReadDTO);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeReadDTO> findEmployee(@PathVariable Long id){
        try {
            Employee employee = employeeService.findEmployee(id);
            EmployeeReadDTO employeeReadDTO = Mapping.mappingFromEmployee(employee);
            return new ResponseEntity<>(employeeReadDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/addresses/{address}")
    public ResponseEntity<EmployeeReadDTO> findByAddress(@PathVariable String address){
        try {
            Employee employee = employeeService.findByAddress(address);
            EmployeeReadDTO employeeReadDTO  = Mapping.mappingFromEmployee(employee);
            return new ResponseEntity<>(employeeReadDTO, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
