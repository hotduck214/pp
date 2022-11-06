package com.cafe.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
	
	int insert(ReplyVO vo);
	int update(ReplyVO vo);
	int edit(ReplyVO vo);
	void updateViewcount(int no);
	
	int count(ReplyVO vo);
	
	List<ReplyVO> list (ReplyVO vo);
	
	int gnoUpdate(int gno);
	int onoUpdate(ReplyVO vo);
	int reply(ReplyVO vo);	
	
	ReplyVO view(int no);
	
	int delete(int no);
	

}
