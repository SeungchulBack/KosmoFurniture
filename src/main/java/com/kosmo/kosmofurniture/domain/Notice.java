package com.kosmo.kosmofurniture.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Notice {
	private Long noticeId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	
}
