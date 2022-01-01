package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.ProductImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImageMapper {

    Long save(ProductImage productImage);
    List<ProductImage> findByProductId(Long productId);
    ProductImage findOneByProductId(Long productId);
    void deleteById(Long productImageId);
    void deleteByProductId(Long productId);
    void deleteAll();
    void setAutoIncToZero();
}
