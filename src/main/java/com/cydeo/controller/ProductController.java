package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
    public String listOfProducts(Model model) {
        return "product/product-list";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {

        return "product/product-create";
    }

    @GetMapping("update")
    public String updateProduct(Model model) {
        return "product/product-update";
    }

}
