package com.cydeo.service.impl;

import com.cydeo.dto.CategoryDto;
import com.cydeo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public void create(CategoryDto dto) {

    }

    @Override
    public void edit(CategoryDto dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
