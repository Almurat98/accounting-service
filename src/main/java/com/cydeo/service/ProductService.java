package com.cydeo.service;

import com.cydeo.dto.ProductDto;
import java.util.List;

public interface ProductService{

    ProductDto findByProductById(Long productID);

    List<ProductDto> getAllProducts();
    void create(ProductDto dto);
    void update(ProductDto dto);
    void delete(Long productId);

}
