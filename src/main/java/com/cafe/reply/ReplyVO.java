package com.cafe.reply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

	public class ReplyVO {

		private int no;
		private String reply_title;
		private String reply_content;
		private Timestamp reply_regdate;
		private int reply_viewcount;
		private int user_no;
		private String filename_org;
		private String filename_real;
		private int gno;
		private int ono;
		private int nested;
		
		private String user_name;
		private int comment_count;
		
		private int page;
		private String stype;
		private String sword;
		private int startIdx;
		private int pageRow;
		
		public ReplyVO() {
//			this.page = 1;
//			this.pageRow = 10;
			this(1, 10);
		}
		public ReplyVO(int page, int pageRow) {
			this.page = page;
			this.pageRow = pageRow;
		}
	}


/*
 gno 답변글쓰기에 있어서 그룹번호
 원글(부모글)과 답변글은 동일한 group no를 가진다.
 */