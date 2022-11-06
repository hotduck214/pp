package com.cafe.free;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

/*
 VO(Value Object)클래스
 db에 저장된 데이터를 저장하는 역할의 클래스
 테이블의 컬럼명과 동일한 이름으로 멤버변수를 만든다.
 값을 저장하는 setter메소드
 값을 리턴하는 getter메소드를 포함한다.
 */

public class FreeVO {
	
	private int free_no;
	private int user_no;
	private String free_title;
	private String free_content;
	private int free_viewcount;
	private Timestamp free_regdate;
	private String free_filenameorg;
	private String free_filenamereal;
	private int comment_count;
	private String user_name;
	
	private int page;
	private String stype;
	private String sword;
	
	
	
	private int startIdx;
	private int pageRow;
	

	// 위와 같이 테이블의 컬럼명과 동일한 멤버변수 만들기
	// -------------------------------------------------- //
	
	
	
	//아래는 디폴트 생성자 - 아래와 같이 2개의 파라미터를 받아 멤버변수에 저장하는 생성자
	// 페이지
	public FreeVO()
	{
		this(1,10); // this 생성자 생성자의 첫 줄에 반드시 생성자를 호출 하여야한다.
		
	}
	public FreeVO(int page, int pageRow) {
		
		this.page = page;					// 컴파일러가 조상기본생성자를 호출한다.
		this.pageRow = pageRow;				
	}
	
}
