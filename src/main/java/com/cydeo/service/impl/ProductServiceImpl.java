package com.cydeo.service.impl;
import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ProductDto findProductById(Long productID) {
        return mapperUtil.convert(productRepository.findById(productID).get(), new ProductDto());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> mapperUtil.convert(product, new ProductDto())).collect(Collectors.toList());
    }


    @Override
    public void create(ProductDto dto) {
        productRepository.save(mapperUtil.convert(dto, new Product()));
    }

    @Override
    public void update(ProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getId());
        Product convertedProduct = mapperUtil.convert(dto, new Product());
        if(product.isPresent()){
            convertedProduct.setId(product.get().getId());
            productRepository.save(convertedProduct);
        }
    }

    @Override
    public void delete(Long productId) {
        Optional<Product> productFound = productRepository.findById(productId);
        productFound.ifPresent(product -> product.setIsDeleted(true));
        productRepository.delete(productFound.get());
    }
}
