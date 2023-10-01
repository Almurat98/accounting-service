package com.cydeo.dto;

import com.cydeo.enums.ClientVendorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientVendorDto {

    private Long id;

    private String clientVendorName;

    private String phone;

    private String website;

    ClientVendorType clientVendorType;

    AddressDto address;

    CompanyDto company;

    boolean hasInvoice;

}
