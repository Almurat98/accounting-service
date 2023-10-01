package com.cydeo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    private String description;
    private CompanyDto companyDto;
    private boolean hasProduct;
}
