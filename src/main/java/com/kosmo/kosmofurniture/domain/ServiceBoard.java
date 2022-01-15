package com.kosmo.kosmofurniture.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ServiceBoard {
	private Long serviceboardId;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createdAt;
	private int memberId;
	private int answerFlag;
	
}
