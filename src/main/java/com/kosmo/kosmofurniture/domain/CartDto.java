package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDto {

    private Long cartId;
    private Integer quantity;
    private String imageFileName;
    private Product product;
}
