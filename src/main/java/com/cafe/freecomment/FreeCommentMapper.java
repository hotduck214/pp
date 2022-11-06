package com.cafe.freecomment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 
 */

@Mapper
public interface FreeCommentMapper {
	
	int insert(FreeCommentVO vo);
	int delete(int no);
	
	List<FreeCommentVO> list(FreeCommentVO vo);
	
	int count(FreeCommentVO vo);

}
