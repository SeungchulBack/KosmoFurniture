package com.kosmo.kosmofurniture.service;

import com.kosmo.kosmofurniture.domain.*;
import com.kosmo.kosmofurniture.exception.NoCartItemException;
import com.kosmo.kosmofurniture.mapper.CartMapper;
import com.kosmo.kosmofurniture.mapper.OrderMapper;
import com.kosmo.kosmofurniture.mapper.OrderProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

    @Transactional
    public Long makeOrder(Long memberId) {

        List<Cart> cartList = cartMapper.findAllByMemberId(memberId);
        if (cartList.size() == 0) throw new NoCartItemException("카트에 상품이 없습니다.");

        Order order = Order.builder()
                .orderStatus("processing")
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build();

        orderMapper.save(order);

        for (Cart cart : cartList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(order.getOrderId());
            orderProduct.setProductId(cart.getProductId());
            orderProduct.setQuantity(cart.getQuantity());
            orderProductMapper.save(orderProduct);
        }
        cartMapper.deleteAllByMemberId(memberId);
        return order.getOrderId();
    }

    public List<OrderDetailDto> orderDetail(Long orderId, MemberPrincipal principal) {

        List<OrderDetailDto> dtos = orderProductMapper.findAllDtosByOrderId(orderId);

        boolean isValidUser = dtos.stream().allMatch(dto -> dto.getMemberId() == principal.getMemberId()) ||
                principal.getAuthorities().toString().equals("[ROLE_ADMIN]");

        if (isValidUser) return dtos;
        else throw new AccessDeniedException("요청할 수 있는 회원이 아닙니다.");
    }

    public void cancelOrder(Long orderId, MemberPrincipal principal) {

        boolean isValidUser = orderMapper.findByOrderId(orderId).getMemberId() == principal.getMemberId() ||
                principal.getAuthorities().toString().equals("[ROLE_ADMIN]");

        if (isValidUser) {
            orderProductMapper.deleteAllByOrderId(orderId);
            orderMapper.deleteByOrderId(orderId);
        } else throw new AccessDeniedException("요청할 수 있는 회원이 아닙니다.");
    }
}


