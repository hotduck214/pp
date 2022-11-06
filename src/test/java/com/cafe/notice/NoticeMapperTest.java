//package com.cafe.notice;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.cafe.notice.NoticeMapper;
//import com.cafe.notice.NoticeService;
//import com.cafe.notice.NoticeVO;
//
//import lombok.extern.log4j.Log4j;
//
//
//// 먼저, 단위 테스트를 스프링과 연동하기 위해 
//@RunWith(SpringJUnit4ClassRunner.class)
//// 환경설정 파일을 명시하기 위해
//@ContextConfiguration("file:src/main/resources/config/context-servlet.xml")
//@Log4j
//
//// file:src/main/resources/config/context-servlet.xml
//public class NoticeMapperTest {
//	
//	@Autowired
//	//NoticeMapper mapper;
//	
//	@Test
//	public void insert() {
//		
//		NoticeVO vo = new NoticeVO();
//		
//		vo.setNotice_title("게시물 제목Test");
//		vo.setNotice_content("게시물 내용Test");
//		
//		int r = 0;
//		
//		for(int i = 0; i < 124; i++) {
//			r += mapper.insert(vo);
//			//service.insert(vo);
//		}
//		log.info("등록개수:" + r);
//	
//	}
	
//	@Test
//	public void update() {
//		
//		NoticeVO vo = new NoticeVO();
//		
//		vo.setNotice_title("수정 게시물 제목Test");
//		vo.setNotice_content("수정 게시물 내용Test");
//		vo.setNotice_no(3);
//		
//		log.info("update:" + mapper.update(vo));
//
//		
//		
//	}
	
//	
//	@Test
//	public void delete() {
//		
//		NoticeVO vo = new NoticeVO();
//		
//		log.info("delete:" +mapper.delete(5));
//		
//	}
	
//	@Test
//	public void view() {
//		mapper.updateViewcount(1);
//		log.info(mapper.view(1));
//		
//	}
	

//}
