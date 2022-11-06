package com.cafe.reply;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe.user.UserVO;


@Controller
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	@GetMapping("/portfolio/reply/index.do")
	public String index(Model model, ReplyVO vo) {
		model.addAttribute("data", service.index(vo));
		
		return "portfolio/reply/index";
	}
	
	@GetMapping("/portfolio/reply/write.do")
	public String write() {
		return "portfolio/reply/write";
	}
	
	@PostMapping("/portfolio/reply/insert.do")
	public String insert(Model model, ReplyVO vo,
			@RequestParam MultipartFile filename,
			HttpServletRequest req) {
		
		// 첨부파일 처리
				if (!filename.isEmpty()) {
					// 파일명 구하기
					String org = filename.getOriginalFilename();
					String ext = org.substring(org.lastIndexOf(".")); // 확장자
					String real = new Date().getTime()+ext;
					
					// 파일저장
					String path = req.getRealPath("/upload/");
					try {
					filename.transferTo(new File(path+real));
					} catch (Exception e) {}
					
					vo.setFilename_org(org);
					vo.setFilename_real(real);
				}
//		HttpSession sess = req.getSession();
//		UserVO mv = (UserVO)sess.getAttribute("loginUserInfo");
//		
//		if(mv != null) vo.setUser_no(mv.getUser_no());
		
		if(service.insert(vo)) {
			model.addAttribute("msg", "정상적으로 저장되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
	} else {
		model.addAttribute("msg", "저장 실패");
		return "common/alert";
		
	}
}
	
	@GetMapping("/portfolio/reply/edit.do")
	public String edit(Model model, ReplyVO vo) {
		ReplyVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "portfolio/reply/edit";
	}
	
	@PostMapping("/portfolio/reply/update.do")
	public String update(Model model, ReplyVO vo) {
		if(service.update(vo)) {
			model.addAttribute("msg", "정상적으로 수정되었습니다.");
			model.addAttribute("url", "view.do?no="+vo.getNo());	
			return "common/alert";
		} else {
			model.addAttribute("msg", "수정 실패");
			return "common/alert";
			
		}
	}
	@GetMapping("/portfolio/reply/reply.do")
	public String reply(ReplyVO vo, Model model) {
		ReplyVO data = service.edit(vo.getNo());
		model.addAttribute("data", data);
		return "portfolio/reply/reply";
	}
	
	@PostMapping("/portfolio/reply/reply.do")
	public String reply(ReplyVO vo, Model model, 
			@RequestParam MultipartFile filename, 
			HttpServletRequest req) {
		
		// 첨부파일 처리
		if (!filename.isEmpty()) {
			// 파일명 구하기
			String org = filename.getOriginalFilename();
			String ext = org.substring(org.lastIndexOf(".")); // 확장자
			String real = new Date().getTime()+ext;
			
			// 파일저장
			String path = req.getRealPath("/upload/");
			try {
			filename.transferTo(new File(path+real));
			} catch (Exception e) {}
			
			vo.setFilename_org(org);
			vo.setFilename_real(real);
		}
		// user_no 저장
//		HttpSession sess = req.getSession();
//		UserVO mv = (UserVO)sess.getAttribute("loginInfo");
//		if (mv != null) vo.setUser_no(mv.getUser_no());
		if (service.reply(vo)) {
			model.addAttribute("msg", "정상적으로 저장되었습니다.");
			model.addAttribute("url", "index.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "저장이 실패했습니다.");
			return "common/alert";
		}
	}
	
	
	@GetMapping("/portfolio/reply/view.do")
	public String view(Model model, ReplyVO vo) {
		ReplyVO data = service.view(vo.getNo());
		model.addAttribute("data", data);
		return "portfolio/reply/view";
	}
	
	@GetMapping("/portfolio/reply/delete.do")
	public String delete(Model model, ReplyVO vo) {
		if(service.delete(vo.getNo())) {
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "index.do");	
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제 실패");
			return "common/alert";
		}
	}

}
