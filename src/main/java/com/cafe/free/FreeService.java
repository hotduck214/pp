package com.cafe.free;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface FreeService {
	
	// 목록
	Map findex(FreeVO vo);
	
	FreeVO view(int free_no);
	FreeVO edit(int free_no);
	boolean update(FreeVO vo); // true면 FreeVO로
	boolean insert(FreeVO vo);
	boolean delete(int free_no);
}

	