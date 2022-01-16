package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.Cart;
import com.kosmo.kosmofurniture.domain.MemberPrincipal;
import com.kosmo.kosmofurniture.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartMapper cartMapper;

    public boolean addCartOrAddQuantity(MemberPrincipal principal, Long productId) {

        Long memberId = principal.getMemberId();

        Integer success = cartMapper.findAllByMemberId(memberId).stream()
                .filter(cart -> cart.getProductId() == productId)
                .findFirst()
                .map(cart -> cartMapper.addQuantity(cart.getCartId()))
                .orElseGet(() -> {
                    Cart cart = Cart.builder()
                            .memberId(memberId)
                            .productId(productId)
                            .quantity(1)
                            .build();
                    return cartMapper.save(cart);
                });

        return (success == 1) ? true : false;
    }
}
