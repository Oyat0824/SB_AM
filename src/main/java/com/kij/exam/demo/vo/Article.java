package com.kij.exam.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String title;
	private String body;
}
