package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
public class Comment {

    private Long commentId;
    private Long memberId;
    private Long productId;
    private String title;
    private String content;
    private Byte star;
    private LocalDateTime createdAt;
}
