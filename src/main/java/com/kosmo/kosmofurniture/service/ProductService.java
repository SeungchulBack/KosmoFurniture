package com.kosmo.kosmofurniture.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.SearchDto;
import com.kosmo.kosmofurniture.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductImageService productImageService;

    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productMapper.findByCategory(category);
    }

    @Transactional
    public boolean deleteProduct(Long productId) {
        try {
            productImageService.deleteImage(productId);
            productMapper.deleteById(productId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public Page<Product> getProductsWithSearchAndPagination(HttpServletRequest request) {
        PageHelper.startPage(request);
        SearchDto searchDto = new SearchDto();
        searchDto.setSearch(request.getParameter("search"));
        searchDto.setSection(request.getParameter("section"));
        return productMapper.findWithSearchAndPagination(searchDto);
    }

    @Transactional
    public boolean updateProduct(Product product) {
        log.debug(String.valueOf(product.getProductId()));
        productMapper.update(product);
        return true;
    }
}
