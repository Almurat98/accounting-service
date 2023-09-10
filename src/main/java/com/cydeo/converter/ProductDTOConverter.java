package com.cydeo.converter;

import com.cydeo.dto.ProductDto;
import com.cydeo.service.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;

public class ProductDTOConverter implements Converter<String, ProductDto> {

    ProductService productService;

    public ProductDTOConverter(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDto convert(String source) {
        if (source == null || source.equalsIgnoreCase("")) {
            return null;
        }
        return productService.findByProductName(source);
    }
}
