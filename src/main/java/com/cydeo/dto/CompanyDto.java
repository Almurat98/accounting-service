package com.cydeo.dto;

import com.cydeo.enums.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDto {
    private Long id;
    private String title;
    private String phone;
    private String website;
    private AddressDto address;
    private CompanyStatus companyStatus;
}
