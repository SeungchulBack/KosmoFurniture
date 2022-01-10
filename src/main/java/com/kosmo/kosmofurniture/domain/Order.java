package com.kosmo.kosmofurniture.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class Order {

    private Long orderId;
    private String orderStatus;
    private Long memberId;
    private LocalDateTime createdAt;
    private String trackingNo;
    private Long totalPrice;
}
