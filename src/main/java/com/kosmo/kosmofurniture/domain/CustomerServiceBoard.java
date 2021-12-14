package com.kosmo.kosmofurniture.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CustomerServiceBoard {

    private Long customerServiceBoardId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long memberId;
    private byte answerFlag;
}
