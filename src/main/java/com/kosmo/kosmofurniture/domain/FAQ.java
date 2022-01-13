package com.kosmo.kosmofurniture.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class FAQ {
	private Long faqId;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createdAt;
	
}
