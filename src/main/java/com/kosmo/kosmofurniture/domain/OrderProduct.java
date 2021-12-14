package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProduct {

    private Long orderProductId;
    private Integer quantity;
    private Long productId;
    private Long orderId;
}
