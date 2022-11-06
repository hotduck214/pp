package com.cafe.free;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//환경설정 파일을 명시하기 위해
@ContextConfiguration("file:src/main/resources/config/context-servlet.xml")
@Log4j

public class FreeMapperTest {

	@Autowired
	FreeMapper mapper;
	
//	@Test
//	public void insert() {
//		
//		FreeVO vo = new FreeVO();
//
//		int r = 0;
//		for(int i=0; i<= 124; i++) {
//			r += mapper.insert(vo);
//			
//			vo.setFree_title("자유게시판 제목 test" + r);
//			vo.setFree_content("자유게시판 내용 test" + r);
//		}
//		
//		log.info("등록개수:" +r);
		
		
//	@Test
//	public void update() {
//		
//		FreeVO vo = new FreeVO();
//		
//		vo.setFree_title("제목 수정 Test");
//		vo.setFree_content("내용 수정 Test");
//		vo.setFree_no(397);
//		
//		log.info("update:" + mapper.update(vo));
//	}
	
//	@Test
//	public void view() {
//		mapper.updateViewcount(1);
//		log.info(mapper.view(10));
//		
//	}
//	@Test
//	public void delete() {
//		log.info("delete:"+mapper.delete(396));
//	}
//	
	
}
