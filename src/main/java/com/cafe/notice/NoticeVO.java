package com.cafe.notice;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class NoticeVO {
	
	private int notice_no;
	private int admin_no;
	private String notice_title;
	private String notice_content;
	private int notice_viewcount;
	private Timestamp notice_regdate;
	private int notice_level;
	private String filename_org;
	private String filename_real;
	
	private int page;
	private String stype;
	private String sword;
	
	
	private int startIdx;
	private int pageRow;
	

	// 페이지
	public NoticeVO()
	{
		this(1,10); // this 생성자 생성자의 첫 줄에 반드시 생성자를 호출 하여야한다.
		
	}
	public NoticeVO(int page, int pageRow) {
		
		this.page = page;					// 컴파일러가 조상기본생성자를 호출한다.
		this.pageRow = pageRow;				// super 들어가야하나?
	}
	
}
