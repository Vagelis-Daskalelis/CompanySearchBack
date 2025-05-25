package com.vaggelis.company.repository;

import com.vaggelis.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Employee Repository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Employee findEmployeeById(Long id);

    Employee findByAddress(String address);

    List<Employee> findByAttributesValue(String value);



    List<Employee> findEmployeesByAttributesValue(String value);




}
