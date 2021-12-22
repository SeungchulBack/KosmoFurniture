package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productMapper.findByCategory(category);
    }

}
