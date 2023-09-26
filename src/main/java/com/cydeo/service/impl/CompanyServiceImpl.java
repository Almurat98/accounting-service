package com.cydeo.service.impl;

import com.cydeo.dto.CompanyDto;
import com.cydeo.entity.Company;
import com.cydeo.enums.CompanyStatus;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CompanyRepository;
import com.cydeo.service.CompanyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public CompanyDto findById(Long id) {
        Optional<Company> companyId = companyRepository.findById(id);
        return mapperUtil.convert(companyId.get(), new CompanyDto());
    }

    @Override
    public List<CompanyDto> getAllCompany() {
        return companyRepository.findAll().stream().map(company -> mapperUtil.convert(company, new CompanyDto())).collect(Collectors.toList());
    }

    @Override
    public void create(CompanyDto companyDto) {
        companyRepository.save(mapperUtil.convert(companyDto, new Company()));
    }

    @Override
    public void update(CompanyDto dto) {
        Optional<Company> company= companyRepository.findById(dto.getId());
        Company convertedCompany = mapperUtil.convert(dto, new Company());
        if(company.isPresent()){
            convertedCompany.setId(company.get().getId());
            companyRepository.save(convertedCompany);
        }
    }

    @Override
    public void delete(Long companyId) {
        //Company can not be deleted only deactivate function available
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyStatus(CompanyStatus.PASSIVE);
        companyRepository.save(company);
    }

}
