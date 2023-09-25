package com.cydeo.controller;

import com.cydeo.dto.CategoryDto;
import com.cydeo.dto.ProductDto;
import com.cydeo.enums.ProductUnit;
import com.cydeo.service.CategoryService;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    CategoryService categoryService;
    List<ProductUnit> productUnits = Arrays.asList(ProductUnit.values());

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String listOfProducts(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("products", productService.getAllProductsForCurrentCompany());
        return "/product/product-list";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategoriesForCurrentCompany());
        model.addAttribute("productUnits", productUnits);
        return "/product/product-create";
    }

    @PostMapping("/create")
    public String insertProduct(@ModelAttribute("newProduct") ProductDto product, @ModelAttribute CategoryDto category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("productUnits", productUnits);
            return "/product/product-create";
        }
            productService.create(product);
            categoryService.create(category);
            return "redirect:/product/product-list";
        }

    @GetMapping("/update/{productId}")
    public String editProduct(@PathVariable("productId") Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("categories", categoryService.getAllCategoriesForCurrentCompany());
        model.addAttribute("productUnits", productUnits);
        return "/product/product-update";
    }

    @PostMapping("/update/{productCode}")
    public String updateProduct(@ModelAttribute("product") ProductDto productDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("productUnits", productUnits);
        }

        productService.update(productDto);
        return "redirect:/product/product-update";
    }

    @GetMapping("delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId, Model model){
        model.addAttribute("product", productService.findProductById(productId));
        productService.delete(productId);
        return "redirect:/product/product-list";
    }
}
