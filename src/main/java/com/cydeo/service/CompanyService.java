package com.cydeo.service;

import com.cydeo.dto.CompanyDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CompanyService {

    List<CompanyDto> getAllCompany();

    CompanyDto findById(Long id);

    void create(CompanyDto companyDto);

    void update(CompanyDto dto);

    void delete(Long companyId);
}
