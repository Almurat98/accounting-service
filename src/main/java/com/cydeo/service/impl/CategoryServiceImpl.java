package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.entity.Category;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CategoryRepository;
import com.cydeo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
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
}
