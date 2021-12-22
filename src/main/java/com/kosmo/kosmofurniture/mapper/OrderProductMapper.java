package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderProductMapper {

    Long save(OrderProduct orderProduct);
    OrderProduct findByOrderId(Long orderId);
    void deleteAll();
    void setAutoIncToZero();
}
