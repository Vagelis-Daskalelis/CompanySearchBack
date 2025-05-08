package com.vaggelis.company.mapper;

import com.vaggelis.company.DTO.attributeDTO.AttributeInsertDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeReadDTO;
import com.vaggelis.company.DTO.attributeDTO.AttributeUpdateDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeInsertDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeReadDTO;
import com.vaggelis.company.DTO.employeeDTO.EmployeeUpdateDTO;
import com.vaggelis.company.model.Attribute;
import com.vaggelis.company.model.Employee;

/***
 * Changes DTOs to Entity
 */
public class Mapping {

    public Mapping() {
    }

    /**Changes the EmployeeInsertDTO to an Employee
     *
     * @param  dto
     * @return new Employee
     */
    public static Employee mappingFromInsert(EmployeeInsertDTO dto){
        return new Employee(null, dto.getName(), dto.getBirthday(), dto.isHasCar(), dto.getAddress());
    }

    /**Changes the EmployeeUpdateDTO to an Employee
     *
     * @param  dto
     * @return new Employee
     */
    public static Employee mappingFromUpdate(EmployeeUpdateDTO dto){
        return new Employee(dto.getId(), dto.getName(), dto.getBirthday(), dto.isHasCar(), dto.getAddress());
    }

    /**Changes the Employee to an EmployeeReadDTO
     *
     * @param employee
     * @return new EmployeeReadDTO
     */
    public static EmployeeReadDTO mappingFromEmployee(Employee employee){
        return new EmployeeReadDTO(employee.getId(), employee.getName(), employee.getBirthday(), employee.isHasCar(), employee.getAddress(), employee.getAttributes());
    }

    /**Changes the AttributeInsertDTO to an Attribute
     *
     * @param dto
     * @return new Attribute
     */
    public static Attribute mappingFromInsert(AttributeInsertDTO dto){
        return new Attribute(null, dto.getName(), dto.getValue());
    }
    /**Changes the AttributeUpdateDTO to an Attribute
     *
     * @param dto
     * @return new Attribute
     */
    public static Attribute mappingFromUpdate(AttributeUpdateDTO dto){
        return new Attribute(dto.getId(), dto.getName(), dto.getValue());
    }

    /**Changes the Attribute to an AttributeReadDTO
     *
     * @param attribute
     * @return
     */
    public static AttributeReadDTO mappingFromAttribute(Attribute attribute){
        return new AttributeReadDTO(attribute.getId(), attribute.getName(), attribute.getValue());
    }
}
