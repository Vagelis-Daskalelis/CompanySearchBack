package com.vaggelis.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;

    public Attribute(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "attributes")
    private List<Employee> employees;


    /**Adds the employee to the employees list
     *
     * @param employee
     */
    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    /**Removes the employee from the employees list
     *
     * @param employee
     */
    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
    }
}
