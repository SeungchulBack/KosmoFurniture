package com.kosmo.kosmofurniture.mapper;

import com.github.pagehelper.Page;
import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.Product;
import com.kosmo.kosmofurniture.domain.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Long save(Product product);
    List<Product> findAll();
    List<Product> findByCategory(String category);
    Page<Product> findWithSearchAndPagination(SearchDto searchDto);
    Product findById(Long productId);
    void deleteById(Long productId);
    void updateStock(Long productId, int stock);
    void update(Product product);
    void deleteAll();
    void setAutoIncToZero();

}