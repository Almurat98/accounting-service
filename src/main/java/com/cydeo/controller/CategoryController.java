package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping("/list")
    public String listAllCategories(){
        return "/category/category-list";
    }

    @GetMapping("/create")
    public String getCreateCategoryForm(){
        return "/category/category-create";
    }

    @PostMapping("/create")
    public String createCategory(){
        return "redirect:/category/category-list";
    }

    @GetMapping("/update/{id}")
    public String editCategoryById(@PathVariable String id){
        return "/category/category-update";
    }
    @PostMapping("/update")
    public String updateCategory(){
        return "redirect:/category/category-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable String id){
        return "redirect:/category/category-list";
    }

}
