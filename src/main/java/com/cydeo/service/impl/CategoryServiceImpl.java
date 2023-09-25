package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Category;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    private final SecurityService securityService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> mapperUtil.convert(category, new CategoryDto())).collect(Collectors.toList());
    }

    @Override
    public void create(CategoryDto dto) {
        categoryRepository.save(mapperUtil.convert(dto, new Category()));
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
