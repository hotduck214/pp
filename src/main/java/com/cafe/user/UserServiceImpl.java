package com.cafe.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.SendMail;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserMapper mapper;
	
	@Override
	public Map index(UserVO vo) {
		int totalCount = mapper.count(vo);
		int totalPage = totalCount / vo.getPageRow();
		
		if(totalCount % vo.getPageRow() > 0) totalPage++;

		//시작 인덱스 / boardMapper.xml 쿼리문과 비교해가면서 메소드를 완성시켜야한다.
		int startIdx = (vo.getPage()-1) * vo.getPageRow();
		// 이후 set으로 호춯	
		vo.setStartIdx(startIdx);
		List<UserVO> list = mapper.list(vo);
		
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
	public int insert(UserVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int emailDupCheck(String user_id) {
		return mapper.emailDupCheck(user_id);
	}
	
	@Override
	public int nickDupCheck(String user_id) {
		return mapper.nickDupCheck(user_id);
	}

	@Override
	public boolean loginCheck(UserVO vo, HttpSession sess) {
		boolean r = false;
		UserVO loginUserInfo = mapper.loginCheck(vo);
		if (loginUserInfo != null) {
			r = true;
			// 로그인 성공시 세션에 저장
			sess.setAttribute("loginUserInfo", loginUserInfo);
		}
		return r;
	}
	
	public UserVO findEmail(UserVO vo) {
		return mapper.findEmail(vo);
	}
	
	public UserVO findPwd(UserVO vo) {
		// update
		UserVO mv = mapper.findPwd(vo);
		if (mv != null) {
			// 임시 비밀번호 생성
			// 영문 두 자리, 숫자 두 자리
			String temp = "";
			for (int i=0; i<3; i++) {
				temp += (char)(Math.random() * 26 + 65);
			}
			for (int i=0; i<3; i++) {
				temp += (int)(Math.random() * 9);
			}
			
			// 임시 비밀번호 update
			vo.setUser_pwd(temp);
			mapper.updateTempPwd(vo);
			
			// email 발송
			SendMail.sendMail("carmania4567@naver.com", vo.getUser_email(), "[식물통합정보사이트]임시비밀번호", "임시비밀번호:"+temp);
			
			return mv;
		} else {
			return null;
		}
	}
	
	@Override
	public boolean delete(int user_no) {
		return mapper.delete(user_no) > 0 ? true : false;
	}

	@Override
	public UserVO view(int user_no) {
		return mapper.view(user_no);
	}
	
	@Override
	public UserVO detail(int user_no) {
		return mapper.detail(user_no);
	}


	@Override
	public UserVO list(int user_no) {
		
		return null;
	}

	@Override
	public int edit(UserVO vo) {
		int no = mapper.edit(vo);
		return no;
	}
	@Override
	public UserVO myInfo(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
