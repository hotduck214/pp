package com.cafe.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 Service를 만들 때 NoticeService와 같이 서비스를 인터페이스로 설계하고,
 NoticeServiceImpl이라는 구현체인 클래스를 생성하여 사용하는 방식
 
 Service, ServiceImpl 패턴으로 설계한 이유
 인터페이스와 구현체를 분리함으로써 
 
 구현체를 독립적으로 확장할 수 있으며, 
 구현체 클래스를 변경하거나 확장할 때 
 이를 사용하는 클라이언트의 코드에 영향을 주지 않도록 하기 위함입니다.
 
 이 같은 추상화를 통한 구현 방식은 객체지향의 특징 중 하나인 
 다형성과 객체지향의 다섯 가지 원칙 중 하나인 OCP(Open Closed Principle) 원칙을 
 가장 잘 실현해주는 설계 방식이라고 할 수 있습니다.
 
 OCP 개방 폐쇄 원칙
 소프트웨어 개체(클래스, 모듈, 함수 등)은 확장에 대해 열려있어야하고,
 수정에 대해서는 닫혀있어야한다.는 프로그램 원칙
 */



@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper mapper;
	
	
	@Override
	public Map nindex(NoticeVO vo) {
		int totalCount = mapper.count(vo); // 총게시물수
		// 총페이지수
		int totalPage = totalCount / vo.getPageRow();
		if (totalCount % vo.getPageRow() > 0) totalPage++;
		
		// 시작인덱스
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		vo.setStartIdx(startIdx);
		List<NoticeVO> list = mapper.list(vo);
		
		// 페이징처리
		int endPage = (int)(Math.ceil(vo.getPage()/10.0)*10);
		int startPage = endPage-9;
		if (endPage > totalPage) endPage = totalPage;
		boolean prev = startPage > 1 ? true : false;
		boolean next = endPage < totalPage ? true : false;
		
		Map map = new HashMap();
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prev", prev);
		map.put("next", next);
		map.put("list", list);
		
		return map;

		
		
	}
	@Override
	public NoticeVO view(int notice_no) {
		mapper.updateViewcount(notice_no);
		return mapper.view(notice_no);
	}

	@Override
	public NoticeVO edit(int notice_no) {
		return mapper.view(notice_no);
	}

	@Override
	public boolean update(NoticeVO vo) {
		return mapper.update(vo) > 0 ? true : false;
	}
	
	@Override
	public boolean insert(NoticeVO vo) {
		return mapper.insert(vo) > 0 ? true : false;
	}
	
	@Override
	public boolean delete(int notice_no) {
		return mapper.delete(notice_no) > 0 ? true : false;
	}
	
	

}
