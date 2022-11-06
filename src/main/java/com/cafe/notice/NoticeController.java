package com.cafe.notice;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class NoticeController {
	
	/* 의존관계 주입(DI)을 할 때 사용하는 어노테이션(Annotation)이며,
	   의존 객체의 타입에 해당하는 빈(Bean)을 찾아 주입하는 역할을 한다.
	   
	   DI란  의존 관계 주입(Dependency Injection)이라고도 하며, 
	   어떤 객체가 사용하는 의존 객체를 직접 만들어 사용하는게 아니라, 
	   주입 받아 사용하는 방법
	   
	   (new 연산자를 이용해서 객체를 생성하는 것이라고 생각하면 된다)
	 */
	
	@Autowired
	NoticeService service;

	/*
	 write
	 view
	 delete
	 edit
	 update
	 
	 */
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	
	/*
	 Model에 데이터를 담을 때 addAttribute() 메소드를 사용하는데,
	 2가지 사용방법
	 1.
	 Model addAttribute(String name, Object value)
	 
	 value라는 객체를 name으로 추가한다.
	 뷰(view) 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다.
	 
	 2.
	 Model addAttribute(Object value)
	
		- value를 추가한다. 
		value의 패키지 이름을 제외한 단순 클래스 이름을 모델 이름으로 사용한다. 
		이 때 첫 글자는 소문자로 처리한다.

		- value가 배열이거나 컬렉션인 경우 
		첫 번째 원소의 클래스 이름 뒤에 "List"를 붙인 것을
		모델 이름으로 사용한다. 
	
	이 경우에도 클래스 이름의 첫자는 소문자로 처리한다.
	 
	 */
	
	@RequestMapping("/main/index.do")
	public String mainIndex(NoticeVO vo, Model model) {
		model.addAttribute("data", service.nindex(vo));
		return "main/index";
	}
	
	
	@GetMapping("portfolio/notice/index.do")
	public String nindex(Model model, NoticeVO vo) {
		model.addAttribute("data", service.nindex(vo));
		return "portfolio/notice/index";
	}
	
	@GetMapping("/portfolio/notice/write.do")
	public String write() {
		
		return "portfolio/notice/write";
	}
	
	@RequestMapping(value="/portfolio/notice/insert.do", method= {RequestMethod.POST})
	public String insert(Model model, NoticeVO vo,
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
		
		vo.setFilename_org(org);

		vo.setFilename_real(real);
	}
	// member_no 저장
//	HttpSession sess = req.getSession();
//	MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
//	vo.setMember_no(mv.getNo());
	
		if(service.insert(vo)) {
			model.addAttribute("msg", "등록완료");
			model.addAttribute("url", "index.do");
			
			return "common/alert";
		} else {
			model.addAttribute("msg", "등록실패");
			return "common/alert";
		}
		
	}
	
	@GetMapping("/portfolio/notice/view.do")
	public String view(Model model, NoticeVO vo) {
		
		NoticeVO data = service.view(vo.getNotice_no());
		model.addAttribute("data", data);
		return "portfolio/notice/view";
		
	}
	
	
	@GetMapping("/portfolio/notice/edit.do")
	public String edit(NoticeVO vo, Model model) {
		
		NoticeVO data = service.edit(vo.getNotice_no());
		model.addAttribute("data", data);
		return "portfolio/notice/edit";
	}
	
	
	@RequestMapping(value="/portfolio/notice/update.do", method= {RequestMethod.POST})
	public String update(Model model, NoticeVO vo,
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
		
		vo.setFilename_org(org);
		System.out.println("org : "+org);

		vo.setFilename_real(real);
	}
	// member_no 저장
//	HttpSession sess = req.getSession();
//	MemberVO mv = (MemberVO)sess.getAttribute("loginInfo");
//	vo.setMember_no(mv.getNo());
		
		if(service.update(vo)) {
			model.addAttribute("msg", "수정완료");
			model.addAttribute("url", "view.do?notice_no="+vo.getNotice_no());
			
			return "common/alert";
			
		} else {
			model.addAttribute("msg", "수정실패");
			return "common/alert";
		}
	}
	
	
	
	@GetMapping ("/portfolio/notice/delete.do")
	public String delete(Model model, NoticeVO vo) {
		if(service.delete(vo.getNotice_no())) {
			
			model.addAttribute("msg", "삭제되었습니다.");
			model.addAttribute("url", "index.do");
			
			return "common/alert";
		
		} else {
			model.addAttribute("msg", "삭제실패");
		}
		
		return "common/alert";
	}
	
	
	
	

}
