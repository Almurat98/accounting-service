package com.cydeo.service;

import com.cydeo.dto.CategoryDto;
import com.cydeo.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();
    void create(CategoryDto dto);
    void edit(CategoryDto dto);
    void delete(Long id);

    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategoriesForCurrentCompany();

}
