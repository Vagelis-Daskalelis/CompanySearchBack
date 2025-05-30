package com.vaggelis.company.DTO.attributeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttributeReadDTO {
    private Long id;
    private String name;
    private String value;
}
