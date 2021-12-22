package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Member;
import com.kosmo.kosmofurniture.domain.Order;
import com.kosmo.kosmofurniture.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    Long save(Order order);
    Order findById(Long orderId);
    void deleteAll();
    void setAutoIncToZero();
}
