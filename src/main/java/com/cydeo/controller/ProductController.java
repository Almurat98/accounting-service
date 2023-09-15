package com.cydeo.controller;

import com.cydeo.dto.ProductDto;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listOfProducts(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("products", productService.getAllProducts());
        return "/product/product-list";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        return "/product/product-create";
    }

    @PostMapping("/create")
    public String insertProduct(){return "redirect:/product/product-list";}

    @GetMapping("/update/{product}")
    public String editProduct(@PathVariable("product") String product, Model model) {
        return "/product/product-update";
    }
    @PostMapping("/update/{product}")
    public String updateProduct(@PathVariable("product") String product){
        return "redirect:/product/product-update";
    }

    @GetMapping("delete/{product}")
    public String deleteProduct(@PathVariable("product") String product){
        return "redirect:/product/product-create";
    }
}
