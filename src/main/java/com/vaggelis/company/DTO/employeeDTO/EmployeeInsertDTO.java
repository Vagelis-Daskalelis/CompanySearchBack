package com.vaggelis.company.DTO.employeeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeInsertDTO {
    private String name;
    private LocalDate birthday;
    private boolean hasCar;
    private String address;
}
