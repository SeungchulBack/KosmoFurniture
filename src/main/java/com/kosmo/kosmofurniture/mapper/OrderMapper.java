package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    Long save(Order order);
    List<Order> findAllByMemberId(Long memberId);
    Order findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
    void deleteAll();
    void setAutoIncToZero();
}
