package com.cafe.freecomment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.user.UserVO;




@Controller
public class FreeCommentController {
	
	@Autowired
	FreeCommentService service;
	
	@GetMapping("/portfolio/freecomment/list.do")
	public String list(FreeCommentVO vo, Model model) {
		model.addAttribute("freecomment", service.index(vo));
		
		return "common/freecomment";
	}
	
//	@GetMapping("/portfolio/freecomment/insert.do")
//	public String insert(FreeCommentVO vo, Model model) {
//		if(service.insert(vo)) {
//			model.addAttribute("msg", "등록완료");
//			model.addAttribute("url", "index.do");
//			
//			return "common/alert";
//		} else {
//			model.addAttribute("msg", "등록실패");
//			return "common/alert";
//		}
//	}
	@GetMapping("/portfolio/freecomment/insert.do")
	public String insert(FreeCommentVO vo, Model model,
			HttpServletRequest req) {
		HttpSession sess = req.getSession();
		UserVO mv = (UserVO)sess.getAttribute("loginUserInfo");
		
		if(mv != null) vo.setUser_no(mv.getUser_no());
		
		model.addAttribute("result", service.insert(vo));
		
		return "common/return";
	}
	
//	@GetMapping("/portfolio/freecomment/delete.do")
//	public String delete(FreeCommentVO vo, Model model) {
//		if (service.delete(vo.getNo())) {
//			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
//			model.addAttribute("url", "index.do");
//			return "common/alert";
//		} else {
//			model.addAttribute("msg", "삭제실패");
//			return "common/alert";
//		}
//		
//	}
	@GetMapping("/portfolio/freecomment/delete.do")
	public String delete(FreeCommentVO vo, Model model) {
		model.addAttribute("result", service.delete(vo));
		
		return "common/return";
	}
	
}