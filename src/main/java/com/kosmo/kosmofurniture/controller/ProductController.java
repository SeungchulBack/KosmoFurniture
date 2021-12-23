package com.kosmo.kosmofurniture.controller;

import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import com.kosmo.kosmofurniture.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }
}
