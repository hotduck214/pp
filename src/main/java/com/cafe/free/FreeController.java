package com.cafe.free;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.user.UserVO;



@Controller
public class FreeController {
	
	@Autowired
	FreeService service;
	
	/*
	 write
	 view
	 delete
	 edit
	 update
	 
	 */
	
	
	@RequestMapping("/portfolio/free/index.do")
	public String index(FreeVO vo, Model model) {
		model.addAttribute("data", service.findex(vo));
		return "portfolio/free/index";
		
	}
	@GetMapping("/portfolio/free/write.do")
	public String write() {
		return "portfolio/free/write";
	}
	
	@RequestMapping(value="/portfolio/free/insert.do", method={RequestMethod.POST})
	public String insert(FreeVO vo, Model model,
			@RequestParam MultipartFile file,
			HttpServletRequest req) {
		
		// 첨부파일 처리
		if (!file.isEmpty()) {
			System.out.println("파일 확인");
			// 파일명 구하기
			String org = file.getOriginalFilename();
			String ext = org.substring(org.lastIndexOf(".")); // 확장자
			String real = new Date().getTime()+ext;
			
			// 파일저장
			String path = req.getRealPath("/upload/");
			try {
				file.transferTo(new File(path+real));
			} catch (Exception e) {}
			
			vo.setFree_filenameorg(org);

			vo.setFree_filenamereal(real);
		}
		HttpSession sess = req.getSession();
		UserVO mv = (UserVO)sess.getAttribute("loginUserInfo");
		
		if(mv != null) vo.setUser_no(mv.getUser_no());
		if(service.insert(vo)) {
			model.addAttribute("msg", "등록완료");
			model.addAttribute("url", "index.do");
			
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록실패");
			return "common/alert";
		}
	}
	
	@GetMapping("/portfolio/free/view.do")
	public String view(Model model, FreeVO vo) {
		
		FreeVO data = service.view(vo.getFree_no());
		model.addAttribute("data", data);
		return "portfolio/free/view";
		
	}

	@GetMapping("/portfolio/free/edit.do")
	public String edit(Model model, FreeVO vo) {
		
		FreeVO data = service.edit(vo.getFree_no());
		model.addAttribute("data",data);
		return "portfolio/free/edit";
	}
	
	@RequestMapping(value="/portfolio/free/update.do", method= {RequestMethod.POST})
	public String update(Model model, FreeVO vo,
		@RequestParam MultipartFile file, 
		HttpServletRequest req) {
	
	// 첨부파일 처리
	if (!file.isEmpty()) {
		System.out.println("파일 확인");
		// 파일명 구하기
		String org = file.getOriginalFilename();
		String ext = org.substring(org.lastIndexOf(".")); // 확장자
		String real = new Date().getTime()+ext;
		
		// 파일저장
		String path = req.getRealPath("/upload/");
		try {
			file.transferTo(new File(path+real));
		} catch (Exception e) {}
		
		vo.setFree_filenameorg(org);

		vo.setFree_filenamereal(real);
	}
	// member_no 저장
//	HttpSession sess = req.getSession();
//	MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
//	vo.setMember_no(mv.getNo());
		
		if(service.update(vo)) {
			model.addAttribute("msg", "수정완료");
			model.addAttribute("url", "view.do?free_no="+vo.getFree_no());
			
			return "common/alert";
			
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	@GetMapping("/portfolio/free/delete.do")
	public String delete(FreeVO vo, Model model) {
		if (service.delete(vo.getFree_no())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제실패");
			return "common/alert";
		}
	}
	
	
}