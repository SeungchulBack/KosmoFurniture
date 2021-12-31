package com.kosmo.kosmofurniture.controller.api;

import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import com.kosmo.kosmofurniture.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductApiController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        log.debug("Authentication : {}", SecurityContextHolder.getContext().getAuthentication().toString());
        log.debug("authority : {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }
}
