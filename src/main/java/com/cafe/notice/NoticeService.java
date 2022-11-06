package com.cafe.notice;
/*
 Service
 
 Request URL에 알맞은 Controller가 수신을 받는다
 @Controller
 
 Service는 알맞은 정보를 가공하여, Controller에게 데이터를 넘긴다.
 
 
 
 */

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface NoticeService {

	/*
	 Map
	 Map 인터페이스는 key:value 형식의 저장방식을 사용하고 있다.
	 
	 key의 경우 값을 저장하고 가져오기 위한 유일한 열쇠
	 value의 경우 Key에 종속된 데이터 이다.
	 
	 key의 경우는 중복을 허용하지 않고 value는 허용한다.
	 
	 
	 Request에 대해 어떤 처리를 할까?
	 Client 가 Request를 보내면
	 Request URL에 알맞은 Controller가 수신한다.
	 
	 Controller는 넘어온 요청을 처리하기 위해 Service를 호출한다.
	 Service는 알맞은 정보를 가공하여 Controller에게 데이터를 넘긴다.
	 
	 Controller는 Service의 결과물을 Client 에게 전달해준다.
	 
	 -
	 Service가 Client의 요청에 대한 올바른 정보를 제공하기 위한 처리를 하는 것을
	 '비즈니스 로직을 수행한다.' 라고도 말한다.
	 
	 interface를 만들 때는 자신이 원하는 값을 입력받고 원하는 값을 히턴할 함수를 선언해준다.
	 Class를 만들어 implements 해서 선언해 둔 함수를 구현해서 원하는 방식대로 동작하도록 설정한다.
	 
	 
	 */
	Map nindex(NoticeVO vo);
	
	NoticeVO view(int notice_no);
	NoticeVO edit(int notice_no);
	
	// 등록처리
	boolean insert(NoticeVO vo);
	boolean update(NoticeVO vo);
	boolean delete(int notice_no);
	
	
	
	

}
