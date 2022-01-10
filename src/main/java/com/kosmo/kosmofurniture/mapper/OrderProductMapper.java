package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.OrderDetailDto;
import com.kosmo.kosmofurniture.domain.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

@Mapper
public interface OrderProductMapper {

    Long save(OrderProduct orderProduct);
    List<OrderProduct> findAllByOrderId(Long orderId);
    @ResultMap("orderDetailDto")
    List<OrderDetailDto> findAllDtosByOrderId(Long orderId);
    OrderProduct findOneByOrderId(Long orderId);
    void deleteAllByOrderId(Long orderId);
    void deleteAll();
    void setAutoIncToZero();
}
