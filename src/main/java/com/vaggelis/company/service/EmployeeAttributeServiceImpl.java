package com.vaggelis.company.service;

import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.model.Employee;
import com.vaggelis.company.repository.AttributeRepository;
import com.vaggelis.company.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeAttributeServiceImpl implements IEmployeeAttributeService{

    private final EmployeeRepository employeeRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeServiceImpl attributeService;

    /**Searches if employee with employeeId if he doesn't exist it throws exception
     * Searches if attribute with attributeId if it doesn't exist it throws exception
     *then searches if an employee already has the attribute if it exists it throws exception
     * else the employee add the attribute
     *
     * returns the employee
     *
     *
     * @param employeeId
     * @param attributeId
     * @return Employee
     * @throws Exception
     */
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

    /**Searches if employee with employeeId if he doesn't exist it throws exception
     * Searches if attribute with attributeId if it doesn't exist it throws exception
     *else it removes the attribute from the employee
     *
     * then it searches if the attribute has any employees with the getEmployeesFromAttributes
     * and if its empty the attribute gets deleted
     *
     * returns the employee
     *
     * @param employeeId
     * @param attributeId
     * @return Employee
     * @throws Exception
     */
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

    /**Helper method that finds all employees that have a certain value
     *
     * @param value
     * @return List<Employee>
     * @throws Exception
     */
    @Override
    public List<Employee> getEmployeesByAttributeValue(String value) throws Exception {
        return employeeRepository.findByAttributesValue(value);
    }


    /**Helper method that searches the employees that each attribute has
     *
     * returns the list of the employees that the attribute has
     *
     * @param id
     * @return List<Employee>
     */
    private List<Employee> getEmployeesFromAttributes(Long id){
        Attribute attribute = attributeRepository.findAttributeById(id);


        return attribute.getEmployees().stream().toList();
    }

}
