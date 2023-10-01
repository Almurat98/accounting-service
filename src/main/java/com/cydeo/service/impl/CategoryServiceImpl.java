package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.entity.Category;
import com.cydeo.entity.Company;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import com.cydeo.service.CompanyService;
import com.cydeo.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    private final SecurityService securityService;
    private final CompanyService companyService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, SecurityService securityService, CompanyService companyService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.companyService = companyService;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> mapperUtil.convert(category, new CategoryDto())).collect(Collectors.toList());
    }

    @Override
    public void create(CategoryDto dto) {
        Category newCategory = mapperUtil.convert(dto, new Category());
        Long companyId = securityService.getLoggedInUser().getCompany().getId();
        Company currentCompany = mapperUtil.convert(companyService.findById(companyId),new Company());
        newCategory.setCompany(currentCompany);
        categoryRepository.save(newCategory);
        log.info("Category created for categoryId : " + newCategory.getId());
    }

    @Override
    public void edit(CategoryDto dto) {
        Optional<Category> category = categoryRepository.findById(dto.getId());
        Category convertedCategory = mapperUtil.convert(dto, new Category());
        if (category.isPresent()) {
            convertedCategory.setId(category.get().getId());
            categoryRepository.save(convertedCategory);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Category> categoryFound = categoryRepository.findById(id);
        categoryFound.ifPresent(category -> category.setIsDeleted(true));
        categoryRepository.save(categoryFound.get());
    }


    @Override
    public CategoryDto getCategoryById(Long id) {
        return mapperUtil.convert(categoryRepository.findById(id), new CategoryDto());
    }

    @Override
    public List<CategoryDto> getAllCategoriesForCurrentCompany() {

        Long companyId = securityService.getLoggedInUser().getCompany().getId();
        List<Category> listByCompany = categoryRepository.findAllByCompanyId(companyId);
        return listByCompany.stream().map(category -> mapperUtil.convert(category, new CategoryDto())).collect(Collectors.toList());

    }
}
