package com.cafe.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/portfolio/user/list.do")
	public String list(Model model, UserVO vo) {
		model.addAttribute("data", service.index(vo));
		return "portfolio/user/list";
	}
	
	@GetMapping("/portfolio/user/view.do")
	public String view(UserVO vo, Model model) {		
		UserVO data = service.view(vo.getUser_no());
		model.addAttribute("data", data);
		return "portfolio/user/view";
	}
	
	@RequestMapping("/portfolio/user/detail")
	public String detail(UserVO vo, Model model) {
		UserVO data = service.detail(vo.getUser_no());
		model.addAttribute("vo", data);
		return "portfolio/user/detail";
	}
	
	@RequestMapping("/portfolio/user/myInfo")
	public String myInfo(UserVO vo, Model model, HttpServletRequest req) {
		HttpSession sess = req.getSession();
		vo = (UserVO) sess.getAttribute("loginUserInfo");
		UserVO data = service.myInfo(vo.getUser_id());
		model.addAttribute("vo", data);
		return "portfolio/user/myInfo";
	}
	
	@GetMapping("/portfolio/user/edit.do")
	public String edit(UserVO vo, Model model, HttpServletRequest req) {
		HttpSession sess = req.getSession();
		vo = (UserVO) sess.getAttribute("loginUserInfo");
		UserVO data = service.myInfo(vo.getUser_id());
		model.addAttribute("vo", data);
		return "portfolio/user/edit";
	}
	
	@PostMapping("/portfolio/user/edit.do")
	public String edit(UserVO vo, Model model) {
		int no = service.edit(vo);
		if (no > 0) {
			vo.setUser_no(no);
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "/pp/portfolio/user/myInfo.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 오류");
			return "common/alert";
		}
	}
	
	@GetMapping("/portfolio/user/delete.do")
	public String delete(UserVO vo, Model model) {
		if(service.delete(vo.getUser_no())) {
		
		model.addAttribute("msg", "탈퇴처리되었습니다.");
		model.addAttribute("url", "list.do");
		return "common/alert";
		} else {
		model.addAttribute("msg", "탈퇴 실패");
		return "common/alert";
		}
	}
	// 로그인 창
	@GetMapping("/portfolio/user/login.do")
	public String login() {
		return "portfolio/user/login";
	}
	
	@PostMapping("/portfolio/user/login.do")
	public String login(UserVO vo, HttpSession sess, Model model) {
		if (service.loginCheck(vo, sess)) {
			return "redirect:/index.do";
		} else {
			model.addAttribute("msg", "아이디와 비밀번호를 확인해 주세요");
			return "common/alert";
		}
		
	}
	
	@GetMapping("/portfolio/user/logout.do")
	public String logout(Model model, HttpServletRequest req) {
		HttpSession sess = req.getSession();
		sess.invalidate(); // 세션초기화(세션객체에있는 모든 값들이 삭제)
		//sess.removeAttribute("loginInfo"); // 세션객체의 해당값만 삭제
		model.addAttribute("msg", "로그아웃되었습니다.");
		model.addAttribute("url", "/portfolio/user/login.do");
		return "common/alert";
	}
	
	@GetMapping("/portfolio/user/join.do")
	public String join() {
		return "portfolio/user/join";
	}
	
	@PostMapping("/portfolio/user/join.do")
	public String join(UserVO vo, Model model) {
	
			if (service.insert(vo) > 0) {
				model.addAttribute("msg", "정상적으로 회원가입되었습니다.");
				model.addAttribute("url", "login.do");
				return "common/alert";
			} else {
				model.addAttribute("msg", "회원가입오류");
				return "common/alert";
			}
		}
	
	@GetMapping("/portfolio/user/welcome.do")
	public String welcome() {
		return "portfolio/user/welcome";
	}
	
	@GetMapping("/portfolio/user/emailDupCheck.do")
	public void emailDupCheck(@RequestParam String id, HttpServletResponse res) throws IOException {
		int count = service.emailDupCheck(id);
		boolean r = false;
		if (count > 0) r = true;
		PrintWriter out = res.getWriter();
		out.print(r);
		out.flush();
	}
	
	@GetMapping("/portfolio/user/nickDupCheck.do")
	public void nickDupCheck(@RequestParam String id, HttpServletResponse res) throws IOException {
		int count = service.nickDupCheck(id);
		boolean r = false;
		if (count > 0) r = true;
		PrintWriter out = res.getWriter();
		out.print(r);
		out.flush();
	}
	
	
	@GetMapping("/portfolio/user/findEmail.do")
	public String findEmail() {
		return "portfolio/user/findEmail";
	}
	
	@PostMapping("/portfolio/user/findEmail.do")
	public String findEmail(Model model, UserVO param) {
		UserVO vo = service.findEmail(param);
		if (vo != null) {
			model.addAttribute("result", vo.getUser_id());
		}
		return "common/return";
	}
	
	@GetMapping("/portfolio/user/findPwd.do")
	public String findPwd() {
		return "portfolio/user/findPwd";
	}
	
	@PostMapping("/portfolio/user/findPwd.do")
	public String findPwd(Model model, UserVO param) {
		UserVO vo = service.findPwd(param);
		if (vo != null) {
			model.addAttribute("result", vo.getUser_email());
		}
		return "common/return";
	}
	

}
