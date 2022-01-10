package com.kosmo.kosmofurniture.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
public class Cart {

    private Long cartId;
    private Long memberId;
    private Long productId;
    private Integer quantity;

}
