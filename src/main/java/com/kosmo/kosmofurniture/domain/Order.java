package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Order {

    private Long orderId;
    private LocalDateTime orderDate;
    private String order_status;
    private Long memberId;
    private LocalDateTime createdAt;
}
