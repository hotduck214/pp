package com.cafe.free;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 Mapper 인터페이스
 Mapper 인터페이스는 Mapping 파일에 기재된 SQL을 호출하기 위한 인터페이스.
 
 */

@Mapper
public interface FreeMapper {
	
	int insert(FreeVO vo);
	int count(FreeVO vo);
	List<FreeVO> list(FreeVO vo);
	FreeVO view(int free_no);
	void updateViewcount(int free_no);
	int update(FreeVO vo);
	int delete(int Free_no);
	
	

}
