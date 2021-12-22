package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Long save(Product product);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    Product findById(Long productId);
    void updateStock(Long productId, int stock);
    void deleteAll();
    void setAutoIncToZero();
}
