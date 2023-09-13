package com.cydeo.service;

import com.cydeo.dto.ProductDto;

public interface ProductService {

    ProductDto findByProductById(Long productID);
}
