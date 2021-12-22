package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Order {

    private Long orderId;
    private String orderStatus;
    private Long memberId;
    private Long totalPrice;
    private String trackingNo;
    private LocalDateTime createdAt;
}
