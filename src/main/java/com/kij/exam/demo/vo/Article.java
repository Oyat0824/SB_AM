package com.kij.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String writerName;
	private String title;
	private String body;
	private int viewCnt;
	private int pointSum;
	private int pointUp;
	private int pointDown;
	
	private boolean actorCanChangeData;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}
}
