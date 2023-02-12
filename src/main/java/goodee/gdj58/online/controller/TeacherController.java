package goodee.gdj58.online.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class TeacherController {
	@Autowired IdService idService;
	@Autowired TeacherService teacherService;
	
	// 회원가입 
	@GetMapping("/addTeacher")
	public String addTeacher(HttpSession session) {
		return "teacher/addTeacher";
	}
	@PostMapping("/addTeacher")
	public String addTeacher(HttpSession session, RedirectAttributes rttr, Teacher teacher) {
		
		int row = teacherService.addTeacher(teacher);
		log.debug("\u001B[31m"+row+"<-- addTeacher row");
		
		String msg = "가입을 실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/addTeacher";
		if(row==1) { // 가입 성공
			msg = "가입을 환영합니다. 로그인 후 이용해주세요.";
			returnUrl = "redirect:/loginTeacher";
			/*
			 *  model 값 저장 후 redirect시 자동으로 parameter에 추가되는 기능이  off되어있다. 
			 *  -> 활성 화 후 -> redirect된 페이지에서도 msg 처리를 해줘야한다
			 *  --> redirect없이 jsp페이지를 갈때 사용 
			 *  RedirectAttributes는 redirect로 리턴하는 코드가 있어야한다. 
			 *  파라미터 값을 1회성 session을 통해 전달함. -> 한번 redirect된 후에는 소멸
			 */
		}
	
		rttr.addFlashAttribute("msg", msg);
		return returnUrl; 
	}
	
	// 로그인 
	@GetMapping("/{path:^loginTeacher$|^loginEmp$|^loginStudent$}")
	public String loginTeacher(HttpSession session, @PathVariable String path ) {
		
		log.debug("\u001B[31m"+path+"<--loginTeacher path");
		
		session.invalidate();
		
		return "teacher/login";
	}
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session, RedirectAttributes rttr, Teacher teacher) {
		Teacher resultTeacher = teacherService.login(teacher);
		log.debug("\u001B[31m"+resultTeacher+"<--loginTeacher resultTeacher");
		
		String msg = "아이디 또는 비밀번호를 잘못입력했습니다. 다시 시도해주세요.";
		String redirectUrl = "redirect:/loginTeacher";
		if(resultTeacher!=null) { // 로그인 성공
			msg = "";
			session.setAttribute("loginTeacher", resultTeacher);
			redirectUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return redirectUrl;
	}
	
	/* 강사 로그인 후 사용 기능*/
	
	// 비밀번호 변경
	@GetMapping("/teacher/modifyPw")
	public String modifyPw() {
		return "teacher/modifyPw";
	}
	@PostMapping("/teacher/modifyPw")
	public String modifyPw(HttpSession session, RedirectAttributes rttr, @RequestParam(value="oldPw", required=true) String oldPw, @RequestParam(value="newPw", required=true) String newPw) {
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int row = teacherService.modifyTeacherPw(loginTeacher.getTeacherNo(), oldPw, newPw);
		log.debug("\u001B[31m"+row+"<--modifyPw row");
		
		String msg = "변경실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/teacher/modifyPw";
		if(row == 1) { // 변경 성공
			msg = "변경되었습니다.";
			returnUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return returnUrl;
	}
	
	/* 사원 기능 */
	
	// 강사 탈퇴 
	@GetMapping(value={"/employee/teacher/removeTeacher", "/teacher/removeTeacher"}) // 파라미터안에 value = { , } 형식으로 작성하면 다중맵핑이 가능
	public String removeTeacher(HttpSession session, @RequestParam(value="teacherNo", required=true) int teacherNo) {
		int row = teacherService.removeTeacher(teacherNo);
		log.debug("\u001B[31m"+row+"<--removeTeacher row ");
		if(row==1) { // 삭제 성공
			session.invalidate();
		}
		return "redirect:/index";
	}
	
	// 강사 목록 출력 
	@GetMapping("/employee/teacher/teacherList")
	public String teacherList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--teacherList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--teacherList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--teacherList searchWord");
		
		// 페이징 
		int count = teacherService.getTeacherCount(searchWord); // 강사 수
		log.debug("\u001B[31m"+count+"<--teacherList count, TeacherController");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 강사가 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "teacher/teacherList";
		}
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} 
		if(currentPage>=lastPage) {
			currentPage = lastPage;
		}
		int startPage = (currentPage-1)/10*10+1;
		int endPage = startPage + 9;
		if(startPage<1) {
			startPage = 1;
		}
		if(endPage>lastPage) {
			endPage = lastPage;
		}
		
		log.debug("\u001B[31m"+lastPage+"<--teacherList lastPage");
		log.debug("\u001B[31m"+startPage+"<--teacherList startPage");
		log.debug("\u001B[31m"+endPage+"<--teacherList endPage");
		
		
		List<Teacher> list = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "teacher/teacherList";
	}
}