package com.vaggelis.company.DTO.employeeDTO;

import com.vaggelis.company.model.Attribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeReadDTO {
    private Long id;
    private String name;
    private LocalDate birthday;
    private boolean hasCar;
    private String address;

    private List<Attribute> attributes;


}
