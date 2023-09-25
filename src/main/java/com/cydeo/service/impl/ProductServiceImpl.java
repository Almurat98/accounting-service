package com.cydeo.service.impl;
import com.cydeo.dto.ProductDto;
import com.cydeo.entity.Product;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import com.cydeo.service.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MapperUtil mapperUtil;

    private final SecurityService securityService;


    public ProductServiceImpl(ProductRepository productRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.productRepository = productRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
    }

    @Override
    public ProductDto findProductById(Long productID) {
        return mapperUtil.convert(productRepository.findById(productID), new ProductDto());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(product -> mapperUtil.convert(product, new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductsForCurrentCompany() {
        Long companyId = securityService.getLoggedInUser().getCompany().getId();
        List<Product> listByCompany = productRepository.findAllByCategoryCompanyIdOrderByCategoryAscNameAsc(companyId);
        return listByCompany.stream().map(product -> mapperUtil.convert(product, new ProductDto())).collect(Collectors.toList());
    }

    @Override
    public void create(ProductDto dto) {
        productRepository.save(mapperUtil.convert(dto, new Product()));
    }

    @Override
    public void update(ProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getId());
        System.out.println( "product from DB"+product);
        Product convertedProduct = mapperUtil.convert(dto, new Product());
        System.out.println("converted"+convertedProduct.toString());
        if(product.isPresent()){
            convertedProduct.setId(product.get().getId());
            convertedProduct.setInsertDateTime(product.get().insertDateTime);
            convertedProduct.setInsertUserId(product.get().insertUserId);
            convertedProduct.setLastUpdateDateTime(LocalDateTime.now());
            convertedProduct.setLastUpdateUserId(securityService.getLoggedInUser().getCompany().getId());

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
