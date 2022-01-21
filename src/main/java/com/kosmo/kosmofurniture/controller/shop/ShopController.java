package com.kosmo.kosmofurniture.controller.shop;

import com.kosmo.kosmofurniture.domain.*;
import com.kosmo.kosmofurniture.mapper.CartMapper;
import com.kosmo.kosmofurniture.mapper.MemberMapper;
import com.kosmo.kosmofurniture.mapper.OrderMapper;
import com.kosmo.kosmofurniture.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    private final CartMapper cartMapper;
    private final MemberMapper memberMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/cart")
    public ModelAndView cartView(
            @AuthenticationPrincipal MemberPrincipal principal,
            @RequestParam(required = false) String token) {

        log.debug(principal.toString());
        log.debug(token);

        Long memberId = memberMapper.findByAccount(principal.getUsername()).getMemberId();

        List<CartDto> cartDtoList = cartMapper.findAllDtoByMemberId(memberId);

        ModelAndView mav = new ModelAndView("shop/cart");
        if (token != null) mav.setViewName("redirect:/shop/cart");
        mav.addObject("cartDtoList", cartDtoList);
        return mav;
    }

    @GetMapping("/order")
    public ModelAndView orderView(
            @AuthenticationPrincipal MemberPrincipal principal,
            @RequestParam(required = false) String token) {

        Long memberId = memberMapper.findByAccount(principal.getUsername()).getMemberId();
        List<Order> orders = orderMapper.findAllByMemberId(principal.getMemberId());

        ModelAndView mav = new ModelAndView("shop/order");
        if (token != null) mav.setViewName("redirect:/shop/order");
        mav.addObject("orders", orders);
        return mav;
    }

    @GetMapping("/order/{orderId}")
    public ModelAndView orderDetail(@PathVariable Long orderId, @AuthenticationPrincipal MemberPrincipal principal) {

        ModelAndView mav = new ModelAndView("shop/order_detail");

        List<OrderDetailDto> orderDetailDtos = orderService.orderDetail(orderId,principal);

        mav.addObject("dtos", orderDetailDtos);
        mav.addObject("orderId", orderId);

        return mav;
    }

    @PostMapping("/order")
    public ModelAndView order(@AuthenticationPrincipal MemberPrincipal principal) {

        orderService.makeOrder(principal.getMemberId());

        return new ModelAndView("redirect:/shop/order");
    }

    @PostMapping("/order/cancel")
    public ModelAndView orderCancel(@RequestParam Long orderId, @AuthenticationPrincipal MemberPrincipal principal) {

        orderService.cancelOrder(orderId, principal);

        return new ModelAndView("redirect:/shop/order");
    }
}
