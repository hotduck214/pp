package com.cafe.freecomment;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FreeCommentVO {
	
	private int no;
	private int free_no;
	private int user_no;
	private int user_name;
	private String comment_content;
	private Timestamp comment_regdate;
	private String tablename;

	private int startIdx;
	private int pageRow;
	private int page;
	
	public FreeCommentVO() {
		this.pageRow = 10;
		this.page = 1;
		
	}
	
}
