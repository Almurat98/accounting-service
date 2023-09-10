package com.cydeo.service.impl;
import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }
    @Override
    public ProductDto findByProductName(String productName) {
       Product product = productRepository.findProductBy(productName).orElseThrow(() -> new NoSuchElementException("Product is not available"));
       return mapperUtil.convert(product, new ProductDto());
    }
}
