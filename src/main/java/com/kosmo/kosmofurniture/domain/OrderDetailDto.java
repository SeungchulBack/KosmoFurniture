package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDetailDto {

    private Long memberId;
    private Long orderId;
    private Integer quantity;
    private String imageFileName;
    private Product product;
}
