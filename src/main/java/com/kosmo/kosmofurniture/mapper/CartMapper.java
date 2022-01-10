package com.kosmo.kosmofurniture.mapper;

import com.kosmo.kosmofurniture.domain.Cart;
import com.kosmo.kosmofurniture.domain.CartDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

@Mapper
public interface CartMapper {

    int save(Cart cart);
    List<Cart> findAllByMemberId(Long memberId);
    @ResultMap("cartDto")
    List<CartDto> findAllDtoByMemberId(Long memberId);
    void deleteById (Long cartId);
    void deleteAllByMemberId(Long MemberId);
    void deleteAll();
}
