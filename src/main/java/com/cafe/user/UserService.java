package com.cafe.user;

import java.util.Map;

import javax.servlet.http.HttpSession;



public interface UserService {

	Map index(UserVO vo);
	
	int insert(UserVO vo);
	
	int emailDupCheck(String user_id);
	int nickDupCheck(String user_id);
	boolean loginCheck(UserVO vo, HttpSession sess);
	
	UserVO findEmail(UserVO vo);
	UserVO findPwd(UserVO vo);
	UserVO view(int user_no);
	UserVO detail(int user_no);
	

	boolean delete(int user_no);
	
	UserVO myInfo(String string);
	UserVO list(int user_no);
	
	int edit(UserVO vo);
}
