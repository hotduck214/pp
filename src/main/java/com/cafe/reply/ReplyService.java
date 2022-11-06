package com.cafe.reply;

import java.util.Map;

public interface ReplyService {
	
	Map index(ReplyVO vo);
	
	ReplyVO view(int no);
	ReplyVO edit(int no);
	
	boolean insert(ReplyVO vo);
	boolean update(ReplyVO vo);
	boolean reply(ReplyVO vo);
	boolean delete(int no);
	

}
