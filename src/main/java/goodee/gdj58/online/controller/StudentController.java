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
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired StudentService studentService;
	@Autowired IdService idService;

	//회원가입
	@GetMapping("/addStudent")
	public String addTeacher(HttpSession session) {
		return "student/addStudent";
	}
	@PostMapping("/addStudent")
	public String addStudent(HttpSession session, RedirectAttributes rttr, Student student) {
		// 아이디 중복확인 
		String idCheck = idService.getIdCheck(student.getStudentId());
		log.debug("\u001B[31m"+idCheck+"<-- addStudent idCheck");
		
		if(idCheck!=null) {
			rttr.addFlashAttribute("msg", "중복된 아이디입니다.");
			return "redirect:/addStudent";
		}
		int row = studentService.addStudent(student);
		log.debug("\u001B[31m"+row+"<-- addStudent row");
		
		String msg = "가입을 실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/addStudent";
		if(row==1) { // 가입 성공
			msg = "가입을 환영합니다. 로그인 후 이용해주세요.";
			returnUrl = "redirect:/loginStudent";
		}
		
		rttr.addFlashAttribute("msg", msg);
		return returnUrl; 
	}
	
	// 로그인
	@GetMapping("/loginStudent")
	public String loginStudent(HttpSession session) {
		session.invalidate();
		return "student/login";
	}
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session, RedirectAttributes rttr, Student student) {
		Student resultStudent = studentService.login(student);
		log.debug("\u001B[31m"+resultStudent+"<--loginStudent resultStudent");
		
		String msg = "아이디 또는 비밀번호를 잘못입력했습니다. 다시 시도해주세요.";
		String redirectUrl = "redirect:/loginStudent";
		if(resultStudent!=null) { // 로그인 성공
			msg = "";
			session.setAttribute("loginStudent", resultStudent);
			redirectUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return redirectUrl;
	}
	
	/* 학생 로그인 후 사용 기능 */

	// 비밀번호 변경
	@PostMapping("/student/modifyPw")
	public String modifyPw(HttpSession session, RedirectAttributes rttr, @RequestParam(value="oldPw", required=true) String oldPw, @RequestParam(value="newPw", required=true) String newPw) {
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int row = studentService.modifyStudentPw(loginStudent.getStudentNo(), oldPw, newPw);
		log.debug("\u001B[31m"+row+"<--modifyPw row");
		
		String msg = "변경실패했습니다. 다시 시도해주세요.";
		String returnUrl = "redirect:/student/modifyPw";
		if(row == 1) { // 변경 성공
			msg = "변경되었습니다.";
			returnUrl = "redirect:/index";
		}
		rttr.addFlashAttribute("msg", msg);
		return returnUrl;
	}
	
	/* 사원 기능 */
	
	// 학생 탈퇴
	@GetMapping(value={"/employee/student/removeStudent","/student/removeStudent"})
	public String removeStudent(HttpSession session, @RequestParam(value="studentNo", required=true) int studentNo) {
		int row = studentService.removeStudent(studentNo);
		log.debug("\u001B[31m"+row+"<--removeStudent row ");
		if(row==1) { // 삭제 성공
			session.invalidate();
		}
		return "redirect:/index";
	}
	
	// 학생 목록 출력 
	@GetMapping("/employee/student/studentList")
	public String studentList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue="1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage
							, @RequestParam(value="searchWord", defaultValue="") String searchWord) {
		
		log.debug("\u001B[31m"+currentPage+"<--studentList currentPage");
		log.debug("\u001B[31m"+rowPerPage+"<--studentList rowPerPage");
		log.debug("\u001B[31m"+searchWord+"<--studentList searchWord");
		
		// 페이징 
		int count = studentService.getStudentCount(searchWord); // 학생 수
		log.debug("\u001B[31m"+count+"<--studentList count");
		if(count==0) {
			String searchMsg = "검색결과가 없습니다.";
			if(searchWord.equals("")) {
				searchMsg = "등록된 학생이 없습니다.";
			}
			model.addAttribute("searchWord", searchWord);
			model.addAttribute("searchMsg", searchMsg);
			return "student/studentList";
		}
		int lastPage = count/rowPerPage;
		if(count%rowPerPage!=0) {
			lastPage+=1;
		}
		if(currentPage<1) {
			currentPage = 1;
		} 
		if(currentPage>lastPage) {
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
			
		log.debug("\u001B[31m"+lastPage+"<--studentList lastPage");
		log.debug("\u001B[31m"+startPage+"<--studentList startPage");
		log.debug("\u001B[31m"+endPage+"<--studentList endPage");
		
		List<Student> list = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		
		model.addAttribute("list", list);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "student/studentList";
	}
}
