package com.cydeo.service;

import com.cydeo.dto.CompanyDto;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {

    CompanyDto findById(Long id);
}
