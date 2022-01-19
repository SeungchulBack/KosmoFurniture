package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
public class Product {

    private Long productId;
    private String name;
    private String description;
    private Long price;
    private String category;
    private Integer stock;
    private LocalDateTime createdAt;
    private ProductImage productImage;

}
