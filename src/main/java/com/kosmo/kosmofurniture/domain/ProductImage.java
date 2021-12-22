package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductImage {

    private Long productImageId;
    private Long productId;
    private String originalFileName;
    private String dbFileName;
}
