package com.vaggelis.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthday;
    private boolean hasCar;
    private String address;

    public Employee(Long id, String name, LocalDate birthday, boolean hasCar, String address) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.hasCar = hasCar;
        this.address = address;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "EmployeeAttribute", joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private List<Attribute> attributes;

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        attribute.addEmployee(this);
    }

    public void removeAttribute(Attribute attribute){
        this.attributes.remove(attribute);
        attribute.removeEmployee(this);
    }


}
